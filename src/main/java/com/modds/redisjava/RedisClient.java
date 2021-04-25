package com.modds.redisjava;

import lombok.Data;

@Data
public class RedisClient {

    private RObj[] argv;
    private RedisDb db;
}
