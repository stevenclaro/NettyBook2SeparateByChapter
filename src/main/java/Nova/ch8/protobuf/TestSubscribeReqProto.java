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
package Nova.ch8.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2014年2月23日
 * @version 1.0
 * 估计是需要proto文件转为java文件。那么如何将java类文件转为proto文件呢？
 */
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
	return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body)
	    throws InvalidProtocolBufferException {
	return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
	SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq
		.newBuilder();
	builder.setSubReqID(1);
	builder.setUserName("Lilinfeng");
	builder.setProductName("Netty Book");
	List<String> address = new ArrayList<>();
	address.add("NanJing YuHuaTai");
	address.add("BeiJing LiuLiChang");
	address.add("ShenZhen HongShuLin");
	builder.addAllAddress(address);
	return builder.build();
    }

    /**
     * @param args
     * @throws InvalidProtocolBufferException
     */
    public static void main(String[] args)
	    throws InvalidProtocolBufferException {
	SubscribeReqProto.SubscribeReq req = createSubscribeReq();
	//取req对象的时候，发生错误。Caused by: com.google.protobuf.InvalidProtocolBufferException: While parsing a protocol message, the input ended unexpectedly in the middle of a field.
	//错误的原因还没有找到
	System.out.println("Before encode : " + req.toString());
	SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
	System.out.println("After decode : " + req.toString());
	System.out.println("Assert equal : --> " + req2.equals(req));

    }

}
