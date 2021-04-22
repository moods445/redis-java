package com.modds.redisjava;

import java.util.Map;

public class RedisDb {
    int id;
    /**
     * 数据库键空间
     */
    Map dict;
    Map expires;
    /**
     * 正处于阻塞状态的key
     */
    Map  blockingKeys;
    /**
     * todo 可以解除阻塞的key
     */
    Map readyKeys;
    /**
     * 正在监视的键
     */
    Map watchedKeys;

    /**
     * 数据库键的平均ttl，统计用
     */
    long  avgTtl;
}
