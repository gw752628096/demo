package com.spring.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisPoolConfig jedisPoolConfig(@Value("${ic.wow.redis.pool.maxTotal}") int maxTotal,
                                           @Value("${ic.wow.redis.pool.maxIdle}") int maxIdle,
                                           @Value("${ic.wow.redis.pool.maxWait}") long maxWaitMillis,
                                           @Value("${ic.wow.redis.pool.testOnBorrow}") boolean testOnBorrow,
                                           @Value("${ic.wow.redis.pool.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMills,
                                           @Value("${ic.wow.redis.pool.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMills);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig config,
                               @Value("${ic.wow.redis.master.host}") String host,
                               @Value("${ic.wow.redis.port}") int port,
                               @Value("${ic.wow.redis.timeout}") int timeout,
                               @Value("${ic.wow.redis.pwd}") String password) {
        return new JedisPool(config, host, port, timeout, password);
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig,
                                                         @Value("${ic.wow.redis.master.host}") String host,
                                                         @Value("${ic.wow.redis.port}") int port,
                                                         @Value("${ic.wow.redis.pwd}") String password,
                                                         @Value("${ic.wow.redis.timeout}") int timeout) {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setTimeout(timeout);
        factory.setUsePool(true);
        return factory;
    }

    /**
     * 序列化的方式不同，使用它可以直接在redis里面看到明文的数据
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        return new StringRedisTemplate(jedisConnectionFactory);
    }
}
