package com.modds.redisjava.adlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class List {
    ListNode head;
    ListNode tail;
    int len;

    public abstract List dup();
    public abstract void free();
    public abstract int match();
}


