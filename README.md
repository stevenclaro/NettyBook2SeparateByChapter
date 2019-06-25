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

ch7在maven中没有，后面考虑从原始的代码中Copy过来

ch9 第9章，运行得不到结果，但是其他章节都可以。
现象是：链接已经建立，但是双方都没有收到现象。关键的问题，是如何寻找客户端发出消息或者服务端接受消息？
        估计还是编码解码的序列化的问题。

第10章，运行得不到结果，估计是jibx没有运行
    下载了ANT，但是build.xml中的代码相对位置被调整过，所以还不能运行。需要重新把build.xml进行复制。

