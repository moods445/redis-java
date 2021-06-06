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

    public static byte[] encoding(String s) {
        if (StringUtils.isBlank(s)) return null;
        List<Byte> buf = new ArrayList<>(8123);

        String[] str = s.trim().split(" ");

        buf.add((byte) '*');
        buf.add((byte) (str.length + '0'));
        buf.add((byte) CR);
        buf.add((byte) LF);
        for (int i = 0; i < str.length; i++) {
            buf.add((byte) '$');
            buf.add((byte) ('0' + str[i].length()));
            buf.add((byte) CR);
            buf.add((byte) LF);
            for (char c : str[i].toCharArray()) {
                buf.add((byte) c);
            }
            buf.add((byte) CR);
            buf.add((byte) LF);
        }

        byte[] res = new byte[buf.size()];
        for (int i = 0; i < buf.size(); i++) {
            res[i] = buf.get(i).byteValue();
        }
        log.debug("encoding: {}", new String(res));
        return res;
    }

    public static StringBuilder crlf(StringBuilder sb) {
        sb.append('\r').append('\n');
        return sb;
    }

}
