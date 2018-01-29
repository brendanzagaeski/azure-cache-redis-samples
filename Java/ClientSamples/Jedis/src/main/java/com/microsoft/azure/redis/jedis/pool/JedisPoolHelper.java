package com.microsoft.azure.redis.jedis.pool;

import com.microsoft.azure.redis.jedis.config.JedisConfiguration;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolMXBean;
import redis.clients.jedis.JedisPool;

public class JedisPoolHelper {

    public static JedisPool getPool(){
        return getPool(null);
    }

    public static JedisPool getPool(String configFilePath){
        JedisConfiguration jedisClientConfiguration = JedisConfiguration.builder().propertyFile(configFilePath).build();
        JedisPoolFactory jedisPoolFactory = new JedisPoolFactory(jedisClientConfiguration);
        return jedisPoolFactory.createJedisPool();
    }

    public static String getPoolConfig(JedisPool jedisPool){
        return String.format("BlockWhenExhausted = %s, TestOnBorrow = %s, TestOnCreate = %s, MaxWaitMills = %s", jedisPool.getBlockWhenExhausted(), jedisPool.getTestOnBorrow(), jedisPool.getTestOnCreate(), jedisPool.getMaxWaitMillis());
    }

    public static String getPoolUsage(JedisPool jedisPool){
        return String.format("Active = %s, Idle = %s, Created = %s, Destroyed = %s, MaxBorrowWait = %s, DestroyedValidation = %s", jedisPool.getNumActive(), jedisPool.getNumIdle(), jedisPool.getCreatedCount(), jedisPool.getDestroyedCount(), jedisPool.getMaxBorrowWaitTimeMillis(), jedisPool.getDestroyedByBorrowValidationCount());
    }
}
