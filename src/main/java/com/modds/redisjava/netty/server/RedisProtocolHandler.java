package com.modds.redisjava.netty.server;

import com.modds.redisjava.netty.RedisProtocolParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class RedisProtocolHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.debug("get msg: {}", msg);
        String[] arg = RedisProtocolParser.decoding(msg);
        log.debug("parse redisProtocol : {}", Arrays.toString(arg));
        ctx.channel().writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error(cause.getMessage(), cause);
        ctx.close();
    }
}
