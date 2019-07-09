package Nova.ch4.NonFunction;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/*---------------------
        作者：superbeyone
        来源：CSDN
        原文：https://blog.csdn.net/superbeyone/article/details/83000803
        版权声明：本文为博主原创文章，转载请附上博文链接！*/
@Sharable
public class ConnectionCountHandler extends ChannelInboundHandlerAdapter {
    private final static AtomicInteger nConnection = new AtomicInteger(0);
//初始化的时候，连接数为0，但是后面每次有active的时候，就会执行增加1
    public ConnectionCountHandler() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("connections: " + nConnection.get());
        }, 0, 10, TimeUnit.SECONDS);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        nConnection.incrementAndGet();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ctx.fireChannelRead(msg);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        nConnection.decrementAndGet();
    }

}
