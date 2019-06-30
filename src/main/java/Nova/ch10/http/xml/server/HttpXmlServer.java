/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Nova.ch10.http.xml.server;

import Nova.ch10.http.xml.codec.HttpXmlResponseEncoder;
import Nova.ch10.http.xml.codec.HttpXmlRequestDecoder;
import Nova.ch10.http.xml.pojo.Order;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class HttpXmlServer {
    public void run(final int port) throws Exception {
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(bossGroup, workerGroup)
		    .channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
		    .childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch)
				throws Exception {
			    ch.pipeline().addLast("http-decoder",
				    new HttpRequestDecoder());
			    ch.pipeline().addLast("http-aggregator",
				    new HttpObjectAggregator(65536));
			    ch.pipeline()
				    .addLast(
					    "xml-decoder",
					    new HttpXmlRequestDecoder(
						    Order.class, true));
			    ch.pipeline().addLast("http-encoder",
				    new HttpResponseEncoder());
			    ch.pipeline().addLast("xml-encoder",
				    new HttpXmlResponseEncoder());
			    ch.pipeline().addLast("xmlServerHandler",
				    new HttpXmlServerHandler());
			}
		    });
	    ChannelFuture future = b.bind(new InetSocketAddress(port)).sync();
	    System.out.println("HTTP订购服务器启动，网址是 : " + "http://localhost:"
		    + port);
	    future.channel().closeFuture().sync();
	} finally {
	    bossGroup.shutdownGracefully();
	    workerGroup.shutdownGracefully();
	}
    }

    public static void main(String[] args) throws Exception {
	int port = 8081;
	if (args.length > 0) {
	    try {
		port = Integer.parseInt(args[0]);
	    } catch (NumberFormatException e) {
		e.printStackTrace();
	    }
	}
	new HttpXmlServer().run(port);
    }
}
