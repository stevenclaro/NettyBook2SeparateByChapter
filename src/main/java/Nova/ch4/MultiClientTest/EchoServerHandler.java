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

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by 李林峰 on 2018/8/11.
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //证明了chanelRegistered是最先连接的，然后是channelActive
    //3次握手之后，就会进行channelRegistered
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("This is chanelRegistered print"+new Date());
        ctx.fireChannelRegistered();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("This is channelActive print"+new Date());
        ctx.fireChannelActive();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("This is channelRead print"+new Date());
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("This is channelUnregistered print"+new Date());
        ctx.fireChannelUnregistered();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
