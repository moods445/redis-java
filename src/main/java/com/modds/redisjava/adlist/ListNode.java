package com.modds.redisjava.adlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListNode<T> {
    ListNode prev;
    ListNode next;
    T value;

    public class ListIter {
        /**
         * 下一个节点
         *
         */
        ListNode next;
        /**
         * 方向
         */
        int direction;
    }
}
