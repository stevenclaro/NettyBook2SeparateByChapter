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

ch9 第9章，运行得不到结果，但是其他章节都可以。明天进行检查。