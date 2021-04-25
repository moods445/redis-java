package com.modds.redisjava.netty.client;

import com.modds.redisjava.netty.RedisProtocolParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * todo not working
 */
public class RedisClientOutHandler extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        if (StringUtils.isNotBlank(msg)) {
            return;
        }
        out.add(RedisProtocolParser.encoding(msg));
    }
}
