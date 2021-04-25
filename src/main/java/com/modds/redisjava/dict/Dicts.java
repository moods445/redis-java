package com.modds.redisjava.dict;

import com.modds.redisjava.RObj;
import com.modds.redisjava.RedisDb;

public class Dicts implements IDict {
    /**
     * todo
     *
     * @param type
     * @param privDataPtr
     * @return
     */
    @Override
    public Dict dictCreate(DictType type, Object privDataPtr) {
        return null;
    }

    public static DictEntry dictFind(RedisDb db, RObj key) {
        //todo
        return null;
    }

    public static int dictDelete(Dict db, final RObj key) {
        return dictGenricDelete(db, key);
    }

    public static int dictGenricDelete(Dict db, RObj key) {
        //todo
        return 1;
    }
}

