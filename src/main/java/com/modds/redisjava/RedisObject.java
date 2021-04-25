package com.modds.redisjava;

import lombok.Data;

import java.util.Date;

@Data
public class RedisObject {
    int type = 4;
    int encoding = 4;

    /**
     * 对象最后被访问的时间
     */
    Date lru;

    int refCount;

    Object ptr;

}
