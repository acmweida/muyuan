package com.muyuan.common.redis.util;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.RedisConst;
import com.muyuan.common.core.context.ApplicationContextHandler;
import com.muyuan.common.core.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IdUtil {

    public static final String MACHINE_CODE_PREFIX = "MACHINE:CODE:";

    private static final long MAX_MACHINE_ID = (1 << 10) - 1;

    private static final long MAX_WORK_ID = (1 << 5) - 1;

    private static long workerId = 1;

    private static long datacentId = 1;

    private static String applicationName = ApplicationContextHandler.getContext().getEnvironment().getProperty("spring.application.name",String.class);

    private static RedisTemplate redisTemplate = ApplicationContextHandler.getContext().getBean("redisTemplate",RedisTemplate.class);

    private static long expire;

    private static final Map<Class<?>,SnowFlake> workers = new ConcurrentHashMap<>();

    static   {
        getMachineCode(redisTemplate,applicationName);
    }

    public static void getMachineCode(RedisTemplate redisTemplate,String applicationName) {
        String localIp = IpUtil.getLocalAddress().getHostAddress();
        long machineId = Math.abs(localIp.hashCode() & MAX_MACHINE_ID);
        while (redisTemplate.hasKey(MACHINE_CODE_PREFIX +applicationName+ machineId)) {
            machineId++;
        }

        redisTemplate.opsForValue().set(MACHINE_CODE_PREFIX +applicationName+ machineId, GlobalConst.SHORT_TRUE_VALUE, 12, TimeUnit.HOURS);
        // redis时间大于机器刷新时间 确保尽量使用停一ID
        if (expire == 0 || expire < System.currentTimeMillis()) {
            expire = System.currentTimeMillis() + 11 * 3600 * 10000;
        }
        workerId = machineId & MAX_WORK_ID;
        datacentId = machineId >> 5;
        log.info("server machine id : {}", machineId);
    }

    private static class SnowFlake {

        /**
         * 起始的时间戳（可设置当前时间之前的邻近时间）
         */
        private final static long START_STAMP = 1645579399860L;

        /**
         * 序列号占用的位数
         */
        private final static long SEQUENCE_BIT = 12;
        /**
         * 机器标识占用的位数
         */
        private final static long MACHINE_BIT = 5;
        /**
         * 数据中心占用的位数
         */
        private final static long DATA_CENTER_BIT = 5;

        /**
         * 每一部分的最大值
         */
        private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
        private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
        private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

        /**
         * 每一部分向左的位移
         */
        private final static long MACHINE_LEFT = SEQUENCE_BIT;
        private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
        private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

        /**
         * 数据中心ID(0~31)
         */
        private final long dataCenterId;
        /**
         * 工作机器ID(0~31)
         */
        private final long machineId;
        /**
         * 毫秒内序列(0~4095)
         */
        private long sequence = 0L;
        /**
         * 上次生成ID的时间截
         */
        private long lastStamp = -1L;

        public SnowFlake(long dataCenterId, long machineId) {
            if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
                throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than " +
                        "0");
            }
            if (machineId > MAX_MACHINE_NUM || machineId < 0) {
                throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
            }
            this.dataCenterId = dataCenterId;
            this.machineId = machineId;
        }

        /**
         * 产生下一个ID
         */
        public synchronized long nextId() {
            long currStamp = getNewStamp();
            if (currStamp < lastStamp) {
                throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
            }

            if (currStamp == lastStamp) {
                //相同毫秒内，序列号自增
                sequence = (sequence + 1) & MAX_SEQUENCE;
                //同一毫秒的序列数已经达到最大
                if (sequence == 0L) {
                    //阻塞到下一个毫秒,获得新的时间戳
                    currStamp = getNextMill();
                }
            } else {
                //不同毫秒内，序列号置为0
                sequence = 0L;
            }

            lastStamp = currStamp;

            // 移位并通过或运算拼到一起组成64位的ID
            return (currStamp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                    | dataCenterId << DATA_CENTER_LEFT       //数据中心部分
                    | machineId << MACHINE_LEFT             //机器标识部分
                    | sequence;                             //序列号部分
        }

        private long getNextMill() {
            long mill = getNewStamp();
            while (mill <= lastStamp) {
                mill = getNewStamp();
            }
            return mill;
        }

        private long getNewStamp() {
            return System.currentTimeMillis();
        }

    }

    public static long createId(Class<?> entityClass) {
        if (expire < System.currentTimeMillis()) {
            getMachineCode(redisTemplate,applicationName);
            workers.clear();
        }

        if (workers.containsKey(entityClass)) {
            return  workers.get(entityClass).nextId();
        }


        SnowFlake  worker = new SnowFlake(workerId, datacentId);
        workers.put(entityClass,worker);

        return worker.nextId();
    }

}
