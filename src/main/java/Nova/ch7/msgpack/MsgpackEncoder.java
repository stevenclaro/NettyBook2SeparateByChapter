package Nova.ch7.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 编码
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack pack = new MessagePack();
        // 将objec类型的POJO对象转码成byte[]
        byte[] bytes = pack.write(o);
        /**
         * Java NIO API自带的缓冲区类功能相当有限，没有经过优化，使用JDK的ByteBuffer操作更复杂。
         * 故而Netty的作者Trustin Lee为了实现高效率的网络传输，重新造轮子，
         * Netty中的ByteBuf实际上就相当于JDK中的ByteBuffer，
         * 其作用是在Netty中通过Channel传输数据。
         */
        // 将这个字节数组写入到缓冲区
        byteBuf.writeBytes(bytes);

    }
}

