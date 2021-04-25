package com.modds.redisjava;

import java.util.concurrent.TimeUnit;

import static com.modds.redisjava.DB.setKey;
import static com.modds.redisjava.Redis.REDIS_ERR;
import static com.modds.redisjava.Redis.REDIS_OK;

public class TString {
    public static final int REDIS_SET_NO_FLAGS = 0;
    public static final int REDIS_SET_NX = 1 << 0;
    public static final int REDIS_SET_XX = 1 << 1;

    SharedObjectsStruct sharedObjectsStruct = SharedObjectsStruct.getInstance();
    NetWorking netWorking;
    DB db;
    RedisServer server;

    public int checkStringLength(RedisClient c, long size) {
        if (size > 512 * 1024 * 1024) {
            netWorking.addReplyError(c, "string exceeds maximum allowed size (512MB)");
            return REDIS_ERR;
        }

        return REDIS_OK;
    }

    public void setGenericCommand(RedisClient c, int flags, RObj key, RObj val, RObj expire, TimeUnit unit, RObj okReply, RObj abortReply) {
        // 过期时间
        long milliseconds = 0;
        if (expire != null) {
            if (unit == TimeUnit.SECONDS) {
                milliseconds = ((long) expire.getPtr()) * 1000L;
            } else {
                milliseconds = (long) expire.getPtr();
            }
        }

        // nx  ex
        if (((flags & REDIS_SET_NX) != 0 && DB.lookUpKeyWrite(c.getDb(), key) != null) ||
                (flags & REDIS_SET_XX) != 0 && DB.lookUpKeyWrite(c.getDb(), key) == null) {
            netWorking.addReply(c, abortReply != null ? abortReply : sharedObjectsStruct.nullbulk);
            return;
        }

        setKey(c.getDb(), key, val);
        server.dirty++;
        if (expire != null) {
            db.setExpire(c.getDb(),key,milliseconds);
        }

        //todo 通知

        // 回复 ok

    }

    /**
     * set key value [NX] [XX] [EX <seconds>] [PX <milliseconds>]
     *
     * @param c
     */
    public void setCommand(RedisClient c) {
        int j;
        RObj expire = null;
        TimeUnit unit = TimeUnit.SECONDS;
        int flags = REDIS_SET_NO_FLAGS;
        for (j = 3; j < c.getArgv().length; j++) {
            String s = (String) c.getArgv()[j].getPtr();
            RObj next = j == c.getArgv().length ? null : c.getArgv()[j + 1];
            if (s.equalsIgnoreCase("nx")) {
                flags |= REDIS_SET_NX;
            } else if (s.equalsIgnoreCase("XX")) {
                flags |= REDIS_SET_XX;
            } else if (s.equalsIgnoreCase("EX")) {
                unit = TimeUnit.SECONDS;
                expire = next;
                j++;
            } else if (s.equalsIgnoreCase("px")) {
                unit = TimeUnit.MILLISECONDS;
                expire = next;
                j++;
            } else {
                netWorking.addReply(c, sharedObjectsStruct.getSyntaxErr());
                return;
            }

            c.getArgv()[2] = ObjectC.tryObjectEncoding(c.getArgv()[2]);
            setGenericCommand(c, flags, c.getArgv()[1], c.getArgv()[2], expire, unit, null, null);
        }

    }
}
