package com.modds.redisjava;

import com.modds.redisjava.adlist.List;
import com.modds.redisjava.adlist.ListNode;

public interface IAdList {
    static int listLength(List l) {
        return l.getLen();
    }

    static ListNode listFirst(List l){
        return l.getHead();
    }

    static ListNode listLast(List l){
        return l.getTail();
    }

    static ListNode ListPrevNode(ListNode n){
        return n.getPrev();
    }

    static ListNode ListNextNode(ListNode n){
        return n.getNext();
    }

    static <T> T  listNodeValue(ListNode<T> n){
        return n.getValue();
    }


}
