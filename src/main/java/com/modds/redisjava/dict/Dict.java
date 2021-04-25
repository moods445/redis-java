package com.modds.redisjava.dict;

public class Dict {
    DictType type;
   Object privData;
   DictHt[] ht = new DictHt[2];
    /**
     * rehash 索引
     * 当rehash不在进行的时候值为-1
     */
   int rehashIdx;
    /**
     * 当前正在运行的迭代器数量
     */
   int iterators;

}
