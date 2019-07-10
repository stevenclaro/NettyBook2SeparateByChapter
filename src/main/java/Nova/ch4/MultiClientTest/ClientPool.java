/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package Nova.ch4.MultiClientTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 李林峰 on 2018/8/5.
 */
public final class ClientPool {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "18081"));
    static AtomicInteger counter=new AtomicInteger(0);
    public static void main(String[] args) throws Exception
    {
//        TimeUnit.SECONDS.sleep(30);
        initClientPool(100);
    }

    static void initClientPool(int poolSize) throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler());
                        p.addLast(new TimeClientHandler());
                    }
                });
        //单一客户端对多个服务器
        List<String> hostList=new ArrayList<String>();
        hostList.add(HOST);
        //poolSize=1;

        for(int i = 0; i < poolSize; i++) {
            for (int j = 0; j < hostList.size(); j++) {
                ChannelFuture future = b.connect(hostList.get(j), PORT).sync();
                future.channel().closeFuture().addListener(
                        new ChannelFutureListener() {

                            //counter.getAndIncrement()
                            @Override
                            public void operationComplete(ChannelFuture future) throws Exception {
                                //业务逻辑处理代码，此处省略...
                                future.channel().close();
                                //这个时候不能把group关闭
                            }
                        }
                );

            }
        }

    }
}
