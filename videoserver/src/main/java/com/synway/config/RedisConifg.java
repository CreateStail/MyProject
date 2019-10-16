package com.synway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConifg {
//
//    @Bean("jedisConnectionFactory")
//    public JedisConnectionFactory getConnectionFactory(){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName("192.168.0.131");
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxWaitMillis(1000);
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        return jedisConnectionFactory;
//    }
//
//    @Bean("StringRedisTemplate")
//    public StringRedisTemplate getRedisTemplate(JedisConnectionFactory jedisConnectionFactory){
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory);
//        return stringRedisTemplate;
//    }

/*      @Bean
      public RedissonClient getRedis(){
          Config config = new Config();
          config.useClusterServers()
                .setScanInterval(2000)
                  .addNodeAddress("redis://192.168.0.131:7001")
                  .addNodeAddress("redis://192.168.0.131:7002")
                  .addNodeAddress("redis://192.168.0.131:7003")
                  .addNodeAddress("redis://192.168.0.131:7004")
                  .addNodeAddress("redis://192.168.0.131:7005")
                  .addNodeAddress("redis://192.168.0.131:7006");
          RedissonClient redisson = Redisson.create(config);
          return redisson;
      }*/
}
