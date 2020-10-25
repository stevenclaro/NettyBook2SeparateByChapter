package Nova.modbus;

import Nova.ch12.struct.Header;
import Nova.ch12.struct.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;
/*
 * Modbus TCP Frame Description
 *  - max. 260 Byte (ADU = 7 Byte MBAP + 253 Byte PDU)
 *  - Length field includes Unit Identifier + PDU
 *
 * <----------------------------------------------- ADU -------------------------------------------------------->
 * <---------------------------- MBAP -----------------------------------------><------------- PDU ------------->
 * +------------------------+---------------------+----------+-----------------++---------------+---------------+
 * | Transaction Identifier | Protocol Identifier | Length   | Unit Identifier || Function Code | Data          |
 * | (2 Byte)               | (2 Byte)            | (2 Byte) | (1 Byte)        || (1 Byte)      | (1 - 252 Byte |
 * +------------------------+---------------------+----------+-----------------++---------------+---------------+
 */
//https://www.cnblogs.com/victorbu/p/10369919.html
public class ModbusDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        ModbusHeader modbusHeader = new ModbusHeader();
        ModbusFunction modbusFunction = new ModbusFunction();
        ModbusFrame modbusFrame = new ModbusFrame(modbusHeader, modbusFunction);
        modbusHeader.setTransactionIdentifier(byteBuf.readShort());
        modbusHeader.setProtocolIdentifier(byteBuf.readShort());
        modbusHeader.setLength(byteBuf.readShort());
        modbusHeader.setUnitIdentifier(byteBuf.readByte());
        //注意在读的过程中，readIndex的指针也在移动
        modbusFrame.setBytefunction(byteBuf.readByte());
        int length=modbusHeader.getLength()-2;
        boolean trueorfalse=length==byteBuf.readableBytes()?true:false;
        byte[] bytes=new byte[length];
        byteBuf.readBytes(bytes);
        modbusFrame.setData(bytes);

        list.add(modbusFrame);
    }
}
