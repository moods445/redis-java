package com.modds.redisjava.dict;

public interface DictType {
    <T> int hashFunction(T key);

}
