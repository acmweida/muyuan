package com.muyuan.common.redis.config;

import com.muyuan.common.redis.listener.KeyDeleteListener;
import com.muyuan.common.redis.manage.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration()
@Import(KeyDeleteListener.class)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfig {

    @Autowired
    private  KeyDeleteListener keyDeleteListener;

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        RedisCacheConfiguration config =RedisCacheConfiguration.defaultCacheConfig();
        factory.setValidateConnection(true); // 断开重连
        RedisCacheConfiguration redisCacheConfiguration = config.serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(lettuceConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisCacheService redisCacheManager1(RedisTemplate redisTemplate) {
        return new RedisCacheService(redisTemplate);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(keyDeleteListener,keyDeleteListener.getTopic());
        return container;
    }


}
