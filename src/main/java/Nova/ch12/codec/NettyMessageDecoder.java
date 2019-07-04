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
package Nova.ch12.codec;


import Nova.ch12.struct.Header;
import Nova.ch12.struct.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lilinfeng
 * @date 2014年3月15日
 * @version 1.0
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
	    int lengthFieldLength) throws IOException {
	super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
	marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
	    throws Exception {
	ByteBuf frame = (ByteBuf) super.decode(ctx, in);
	//如果为空则说明是一个半包消息，直接返回继续有I/O线程读取后续的码流
	if (frame == null) {
	    return null;
	}

	NettyMessage message = new NettyMessage();
	Header header = new Header();
		//注意在读的过程中，readIndex的指针也在移动
	header.setCrcCode(frame.readInt());
	header.setLength(frame.readInt());
	header.setSessionID(frame.readLong());
	header.setType(frame.readByte());
	header.setPriority(frame.readByte());

	int size = frame.readInt();
	if (size > 0) {
	    Map<String, Object> attch = new HashMap<String, Object>(size);
	    int keySize = 0;
	    byte[] keyArray = null;
	    String key = null;
	    for (int i = 0; i < size; i++) {
		keySize = frame.readInt();
		keyArray = new byte[keySize];
		frame.readBytes(keyArray);
		key = new String(keyArray, "UTF-8");
		attch.put(key, marshallingDecoder.decode(frame));
	    }
	    keyArray = null;
	    key = null;
	    header.setAttachment(attch);
	}
	if (frame.readableBytes() > 4) {
	    message.setBody(marshallingDecoder.decode(frame));
	}
	message.setHeader(header);
	return message;
    }
}
