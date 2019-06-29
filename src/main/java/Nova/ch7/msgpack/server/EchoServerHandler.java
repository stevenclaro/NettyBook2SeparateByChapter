package Nova.ch7.msgpack.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.List;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 打印接收到的消息
        List<Object> list = (List<Object>)msg;
        System.out.println(list);
        System.out.println("服务端收到客户端发来的消息是：" + list);
        ctx.writeAndFlush(list);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
/*
---------------------
        作者：wildwolf_001
        来源：CSDN
        原文：https://blog.csdn.net/wildwolf_001/article/details/81330110
        版权声明：本文为博主原创文章，转载请附上博文链接！*/
