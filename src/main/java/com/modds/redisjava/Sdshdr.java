package com.modds.redisjava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sdshdr {
    int len;
    int free;
    char[] buf;
}
