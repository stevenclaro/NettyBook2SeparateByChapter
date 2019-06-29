# NettyBook2SeparateByChapter
NettyBook2SeparateByChapter
该书以Netty5.0为基础，为了实用化，将其修改为Netty4.X的版本
以章节作为基础进行编制

ch3调整的地方
在5.0中，ChannelHandlerAdapter接口丰富，比4.X多一些接口。
代码调整，继承为ChannelInboundHandlerAdapter
//public class TimeServerHandler extends ChannelHandlerAdapter {
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

Client端也是这样调整的。ChannelInboundHandlerAdapter 代替ChannelHandlerAdapter
在4.x版本中，在ChanelHandleAdapter中是没有read的方法

ch7在maven中没有，参考https://blog.csdn.net/wildwolf_001/article/details/81330110
把代码补齐了

ch9 第9章，客户端无法发送，服务器也没有收到，
现象是：链接已经建立，但是双方都没有收到现象。关键的问题，是如何寻找客户端发出消息或者服务端接受消息？
        估计还是编码解码的序列化的问题。
        问题已经解决：主要原因是客户端的序列化少了一个依赖，在POM中已经添加上去
    

第10章，运行得不到结果，估计是jibx没有运行
    下载了ANT，但是build.xml中的代码相对位置被调整过，所以还不能运行。需要重新把build.xml进行复制。
    已经成功了服务器端，还有部分没有成功

11章
12章，服务器能启动，客户端无法启动，报buildUnMarshalling的错误


