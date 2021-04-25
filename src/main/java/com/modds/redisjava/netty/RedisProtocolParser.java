package com.modds.redisjava.netty;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RedisProtocolParser {

    public static final int SPLIT = '\r' + '\n';
    public static final int CR = '\r';
    public static final int LF = '\n';

    /**
     * *3
     *
     * @param s
     * @return
     */
    public static String[] decoding(String s) {
        if (StringUtils.isBlank(s)) return null;
        List<String> res = new ArrayList<>();

        String[] init = s.trim().split("\\\\r\\\\n");
        for (int i = 2; i < init.length; i += 2) {
            res.add(init[i]);
        }
        return res.toArray(new String[]{});
    }

    public static String encoding(String s) {
        if (StringUtils.isBlank(s)) return null;
        StringBuilder sb = new StringBuilder();

        String[] str = s.trim().split(" ");

        sb.append('*')
                .append(str.length + '0')
                .append(CR)
                .append(LF);
        for (int i = 0; i < str.length; i++) {
            sb.append('$')
                    .append('0' + str[i].length())
                    .append(CR)
                    .append(LF)
                    .append(str[i])
                    .append(CR)
                    .append(LF);
        }

        log.debug("encoding: {}", sb);
        return sb.toString();
    }

    public static StringBuilder crlf(StringBuilder sb) {
        sb.append('\r').append('\n');
        return sb;
    }

}
