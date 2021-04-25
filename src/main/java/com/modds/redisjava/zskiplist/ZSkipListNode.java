package com.modds.redisjava.zskiplist;

import com.modds.redisjava.RObj;

public class ZSkipListNode {
    RObj obj;
    double score;
    ZSkipListNode backWard;

    ZSkipListLevel level[];

    public static class ZSkipListLevel {
        ZSkipListNode forward;
        /**
         * 跨度
         */
        int span;
    }
}
