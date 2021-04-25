package com.modds.redisjava.netty.client;

import com.modds.redisjava.netty.RedisProtocolParser;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class Client {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringDecoder())
                                    .addLast(new RedisClientHandler());
//                                    .addLast(new RedisClientOutHandler())
//                                    .addLast(new StringEncoder());
                        }
                    });

            log.info("Client starting...");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6380).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("Client started!");
                    } else {
                        log.info("Client start failed!");
                    }
                }
            });
            Channel channel = channelFuture.channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String in = br.readLine();
            while (!"exit".equals(in)) {
                if (StringUtils.isNotBlank(in)) {
                    String ened = RedisProtocolParser.encoding(in);
                    String cmd = Arrays.stream(in.split(" ")).map(s -> "\"" + s + "\"").collect(Collectors.joining(" "));
//                    log.info("INPUT: {}", cmd);
                    log.info("INPUT: {}", ened.toCharArray());

                    channel.writeAndFlush(ened.toCharArray());
                }
                in = br.readLine();
            }
            channel.writeAndFlush(in);
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
