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
package Nova.ch6.serializable;

import com.alibaba.fastjson.JSON;
import org.msgpack.MessagePack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Administrator
 * @date 2014年2月23日
 * @version 1.0
 */
public class TestUserInfo {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	UserInfo info = new UserInfo();
	info.buildUserID(100).buildUserName("Welcome to Netty");
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	ObjectOutputStream os = new ObjectOutputStream(bos);
	os.writeObject(info);
	os.flush();
	os.close();
	byte[] b = bos.toByteArray();
	System.out.println("The jdk serializable length is : " + b.length);
	System.out.println("-------------------------------------");
	//在测试MessagePack的时候，需要注意需要在类上增加一个@message的注解，否则会报错
		MessagePack pack = new MessagePack();
		// 将objec类型的POJO对象转码成byte[]
		byte[] bytes = pack.write(info);

	System.out.println("The msgPack serializable length is : " + bytes.length);
	bos.close();
	System.out.println("-------------------------------------");
	System.out.println("The byte array codeC() serializable length is ,这个是字节按照字节手动编码，采用ByteBuffer : "
		+ info.codeC().length);
		System.out.println("-------------------------------------");
	System.out.println("The byte array codeCByByteBuf serializable length is，这个是字节按照字节手动编码，ByteBuf : "
				+ info.codeCByByteBuf().length);

		System.out.println("-------------------------------------");
	//试验一下fastjson的序列化消息
		// 将Java对象序列化为Json字符串
		    String objectToJson = JSON.toJSONString(info);
		    byte[] objectToJsonbytes=objectToJson.getBytes();
		   /* System.out.println(objectToJson);
		     // 将Json字符串反序列化为Java对象
		     User user = JSON.parseObject(objectToJson, User.class);*/
		UserInfo userInfo=JSON.parseObject(objectToJson, UserInfo.class);

		     System.out.println("采用Json序列化之后，它的长度 is:"+objectToJsonbytes.length);
	}

}
