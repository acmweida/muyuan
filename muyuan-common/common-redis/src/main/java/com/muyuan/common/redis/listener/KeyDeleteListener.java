package com.muyuan.common.redis.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

/**
 * @ClassName KeyDeleteListener
 * Description Key删除监听器
 * @Author 2456910384
 * @Date 2022/11/21 16:59
 * @Version 1.0
 */
@Component
@Slf4j
@Data
public class KeyDeleteListener implements MessageListener {

    private final PatternTopic topic = new PatternTopic("__keyevent@0__:del");

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(pattern);
        String msg = new String(message.getBody());
        log.info("key event -> topic : {} content: {}",topic,msg);
    }
}
