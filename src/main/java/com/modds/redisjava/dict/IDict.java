package com.modds.redisjava.dict;

import java.util.Dictionary;

public interface IDict {
    static int DICT_OK = 0;
    static int DICT_ERR = 1;

    /**
     * 初始化大小
     */
    static int DICT_HT_INITIAL_SIZE = 4;

    Dict dictCreate(DictType type,Object privDataPtr);

}
