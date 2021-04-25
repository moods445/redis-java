package com.modds.redisjava.dict;

import lombok.Data;

@Data
public class DictEntry<K,V> {
    K key;
    V v;
    DictEntry next;
}
