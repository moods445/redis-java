package com.modds.redisjava.dict;

import java.util.Dictionary;

public class DictIterator {
    Dict d;
    /**
     * 正在被迭代的hash表号码
     */
    int table;

    /**
     * 迭代器当前所指向的哈希表索引位置
     */
    int index;

    /**
     * 标识这个迭代器是否安全
     */
    int safe;

    DictEntry entry;

    DictEntry nextEntry;

    long fingerPrint;
}
