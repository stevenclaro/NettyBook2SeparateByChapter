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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

/**
 * @author Lilinfeng
 * @date 2014年3月14日
 * @version 1.0
 */
@Sharable
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
	marshaller = MarshallingCodecFactory.buildMarshalling();
    }

    protected void encode(Object msg, ByteBuf out) throws Exception {
	try {
		//获取当前的writeIndex的值
	    int lengthPos = out.writerIndex();
	    //在当前的writerIndex处写入 LENGTH_PLACEHOLDER 个字节值，并将writerIndex 增加 LENGTH_PLACEHOLDER=4
	    out.writeBytes(LENGTH_PLACEHOLDER);
	    ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
	    marshaller.start(output);
	    marshaller.writeObject(msg);
	    marshaller.finish();
	    //在协议定义的时候，length 是整型 int ，长度32，包括消息头和消息体。
	    //设定给定索引处的int值，但并不影响索引值
	//debug的时候，检测一下readindex及writeindex的值
	    out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
	} finally {
	    marshaller.close();
	}
    }
}
