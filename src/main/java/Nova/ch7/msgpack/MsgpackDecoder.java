package Nova.ch7.msgpack;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * 解码
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf>{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        // 获取需要解码数据的长度
        final int length = byteBuf.readableBytes();
        // 新创建一个字节数组，其长度设置为上面获取的长度
        array = new byte[length];
        // 将要解码的数据填充到新创建的数组中
        byteBuf.getBytes(byteBuf.readerIndex(),array,0, length);
        MessagePack pack = new MessagePack();
        // 调用MessagePack的read方法将其反序列化为object对象
        // 将解码后的对象加入到解码对象列表
        list.add(pack.read(array));
    }
}
