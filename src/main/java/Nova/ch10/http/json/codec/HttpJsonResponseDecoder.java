package Nova.ch10.http.json.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.List;


/**
 * Created by carl.yu on 2016/12/16.
 */
public class HttpJsonResponseDecoder extends AbstractHttpJsonDecoder<FullHttpResponse> {

    public HttpJsonResponseDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    /**
     * 构造器
     *
     * @param clazz   解码的对象信息
     * @param isPrint 是否需要打印
     */
    public HttpJsonResponseDecoder(Class<?> clazz, boolean isPrint) {
        super(clazz, isPrint);
    }

    /**
     * @param ctx channel上下文
     * @param msg 消息
     * @param out 输出集合
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List<Object> out) throws Exception {
        System.out.println("开始解码...");
        out.add(
                new HttpJsonResponse(msg, decode0(ctx, msg.content()))
        );
    }


}