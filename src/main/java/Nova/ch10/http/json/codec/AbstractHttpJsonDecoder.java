package Nova.ch10.http.json.codec;


import Nova.ch10.http.json.FastJsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;

/**
 * Created by carl.yu on 2016/12/16.
 */
public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {
    private Class<?> clazz;
    private boolean isPrint;
    private final static Charset UTF_8 = Charset.forName("UTF-8");

    protected AbstractHttpJsonDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    protected AbstractHttpJsonDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    protected Object decode0(ChannelHandlerContext ctx, ByteBuf body) {
        String content = body.toString(UTF_8);
        if (isPrint)
            System.out.println("The body is : " + content);
        //Object result = FastJsonUtils.convertJSONToObject(content, clazz);
        Object result=FastJsonUtils.convertJsonToObject(content,clazz);
        return result;
    }


}
