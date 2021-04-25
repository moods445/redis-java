package com.modds.redisjava;

import com.modds.redisjava.dict.DictEntry;
import com.modds.redisjava.dict.Dicts;

public class DB {
    public static void setKey(RedisDb db, RObj key, RObj val) {
        if (lookUpKeyWrite(db, key) == null) {
            dbAdd(db, key, val);
        } else {
            dbOverwrite(db, key, val);
        }

        incrRefCount(val);
        removeExpire(db, key);

//        signalModifiedKey(db, key);
    }

    private static void removeExpire(RedisDb db, RObj key) {
//todo
    }

    public static void setExpire(RedisDb db, RObj key, long when) {
        //todo
    }

    private static void incrRefCount(RObj val) {
        val.refCount++;
    }

    private static void dbOverwrite(RedisDb db, RObj key, RObj val) {
        //todo
    }

    private static void dbAdd(RedisDb db, RObj key, RObj val) {

        //todo

    }

    public static RObj lookUpKeyWrite(RedisDb db, RObj key) {
        expireIfNeeded(db, key);
        return lookUpKey(db, key);
    }

    public static int expireIfNeeded(RedisDb db, RObj key) {
        //todo
        return 1;
    }

    private static RObj lookUpKey(RedisDb db, RObj key) {
        DictEntry entry = Dicts.dictFind(db, key);
        return (RObj) entry.getV();
    }
}
