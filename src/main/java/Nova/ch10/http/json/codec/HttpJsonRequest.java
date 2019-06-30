package Nova.ch10.http.json.codec;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author Lilinfeng
 * @version 1.0
 * @date 2014年3月1日
 */
public class HttpJsonRequest {

    private FullHttpRequest request;
    private Object body;

    public HttpJsonRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    /**
     * @return the request
     */
    public final FullHttpRequest getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public final void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    /**
     * @return the object
     */
    public final Object getBody() {
        return body;
    }

    /**
     * @param object the object to set
     */
    public final void setBody(Object body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpJsonRequest [request=" + request + ", body =" + body + "]";
    }
}