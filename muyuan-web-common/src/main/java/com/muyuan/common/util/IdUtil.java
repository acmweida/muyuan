package com.muyuan.common.util;

import com.muyuan.common.constant.RedisConst;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class IdUtil {

    public static final String MACHINE_CODE_MAP = "MACHINE:CODEMAP";

    private static final long MAX_MACHINE_ID = 1 << 10;

    private static final long MAX_WORK_ID = (1 << 5) -1;

    private static long workerId = 1;

    private static long datacentId = 1;

    private static final String NAME_PREFIX = "my_";

    private static IdWorker worker = null;

    private static Random random = new Random();

    private RedisTemplate redisTemplate;

    public IdUtil(@Autowired RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        getMachineCode(redisTemplate);
        worker =new IdWorker(workerId,datacentId,1);
    }

    public static void  getMachineCode(RedisTemplate redisTemplate ) {
        HashOperations hashOperations = redisTemplate.opsForHash();

        String localIp = IpUtil.getLocalAddress().getHostAddress();
        long machineId =  Math.abs(localIp.hashCode() % MAX_MACHINE_ID);
        while ( hashOperations.hasKey(MACHINE_CODE_MAP,String.valueOf(machineId))){
           machineId++;
        }
        hashOperations.put(MACHINE_CODE_MAP,String.valueOf(machineId), RedisConst.SHORT_TRUE_VALUE);
        workerId = machineId & MAX_WORK_ID;
        datacentId = machineId >> 5;
        long finalMachineId = machineId;
        log.info("server machine id : {}",machineId);
        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () ->  {
                            redisTemplate.opsForHash().delete(MACHINE_CODE_MAP, String.valueOf(finalMachineId));
                            log.info("server machine id : {} cancel registration",finalMachineId);
                        }
                )
        );
    }

    private static class IdWorker{

        //下面两个每个5位，加起来就是10位的工作机器id
        private long workerId;    //工作id
        private long datacenterId;   //数据id
        //12位的序列号
        private long sequence;

        public IdWorker(long workerId, long datacenterId, long sequence){
            // sanity check for workerId
            if (workerId > maxWorkerId || workerId < 0) {
                throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
            }
            if (datacenterId > maxDatacenterId || datacenterId < 0) {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
            }
            System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                    timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

            this.workerId = workerId;
            this.datacenterId = datacenterId;
            this.sequence = sequence;
        }

        //初始时间戳
        private long twepoch = 1288834974657L;

        //长度为5位
        private long workerIdBits = 5L;
        private long datacenterIdBits = 5L;
        //最大值
        private long maxWorkerId = -1L ^ (-1L << workerIdBits);
        private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        //序列号id长度
        private long sequenceBits = 12L;
        //序列号最大值
        private long sequenceMask = -1L ^ (-1L << sequenceBits);

        //工作id需要左移的位数，12位
        private long workerIdShift = sequenceBits;
        //数据id需要左移位数 12+5=17位
        private long datacenterIdShift = sequenceBits + workerIdBits;
        //时间戳需要左移位数 12+5+5=22位
        private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

        //上次时间戳，初始值为负数
        private long lastTimestamp = -1L;

        public long getWorkerId(){
            return workerId;
        }

        public long getDatacenterId(){
            return datacenterId;
        }

        public long getTimestamp(){
            return System.currentTimeMillis();
        }

        //下一个ID生成算法
        public synchronized long nextId() {
            long timestamp = timeGen();

            //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
            if (timestamp < lastTimestamp) {
                System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        lastTimestamp - timestamp));
            }

            //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                if (sequence == 0) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0;
            }

            //将上次时间戳值刷新
            lastTimestamp = timestamp;

            /**
             * 返回结果：
             * (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数
             * (datacenterId << datacenterIdShift) 表示将数据id左移相应位数
             * (workerId << workerIdShift) 表示将工作id左移相应位数
             * | 是按位或运算符，例如：x | y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。
             * 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
             */
            return ((timestamp - twepoch) << timestampLeftShift) |
                    (datacenterId << datacenterIdShift) |
                    (workerId << workerIdShift) |
                    sequence;
        }

        //获取时间戳，并与上次时间戳比较
        private long tilNextMillis(long lastTimestamp) {
            long timestamp = timeGen();
            while (timestamp <= lastTimestamp) {
                timestamp = timeGen();
            }
            return timestamp;
        }

        //获取系统时间戳
        private long timeGen(){
            return System.currentTimeMillis();
        }

    }

    public long  createId() {
        return worker.nextId();
    }


    public static String randomString(int length) {
        StringBuffer name = new StringBuffer();
        Random random = new Random();
        char temp = 'a';
        int t = 0;
        for (int i=0;i<length;i++) {
            t = random.nextInt(61);
            if (t < 10) {
                temp = (char) (48 + t);
            } else if (t < 37) {
                temp = (char) (55 + t);
            } else {
                temp = (char) (60 + t);
            }
            name.append(temp);
        }

        return name.toString();
    }

    public static String createUserName() {
        StringBuffer name = new StringBuffer(NAME_PREFIX);
        name.append(randomString(7));
        name.append((random.nextInt(900)+100));
        return name.toString();
    }

    public static long createUserNo() {
        StringBuffer userNoStr = new StringBuffer().append(random.nextInt(4)+5);
        userNoStr.append(DateTime.now().toString("MMyyddHHssmm")).append(System.currentTimeMillis() % 1000)
                .append(random.nextInt(9));;
        return Long.valueOf(userNoStr.toString());
    }

}
