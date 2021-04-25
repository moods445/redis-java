package com.modds.redisjava;

import lombok.Data;

@Data
public class SharedObjectsStruct {
    private static final SharedObjectsStruct instance = new SharedObjectsStruct();
    RObj crlf, syntaxErr, nullbulk;


    public static SharedObjectsStruct getInstance() {
        return instance;
    }

}
