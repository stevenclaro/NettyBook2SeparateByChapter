# NettyBook2SeparateByChapter
NettyBook2SeparateByChapter
该书以Netty5.0为基础，为了实用化，将其修改为Netty4.X的版本。在POM中的版本是4.1.28.Final。在2019年10月25日，当前的版本是4.1.43Final
以章节作为基础进行编制

ch3调整的地方
在5.0中，ChannelHandlerAdapter接口丰富，比4.X多一些接口。
代码调整，继承为ChannelInboundHandlerAdapter
//public class TimeServerHandler extends ChannelHandlerAdapter {
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

Client端也是这样调整的。ChannelInboundHandlerAdapter 代替ChannelHandlerAdapter
在4.x版本中，在ChanelHandleAdapter中是没有read的方法

 在第4章中，增加了Netty的连接数统计，目录是NonFunction ,但是对于客户端没有优雅的关闭，需要进一步研发进行关闭。
 
ch7在maven中没有，参考https://blog.csdn.net/wildwolf_001/article/details/81330110
把代码补齐了

ch9 第9章，客户端无法发送，服务器也没有收到，
现象是：链接已经建立，但是双方都没有收到现象。关键的问题，是如何寻找客户端发出消息或者服务端接受消息？
        估计还是编码解码的序列化的问题。
   问题已经解决：主要原因是客户端的序列化少了一个依赖，在POM中已经添加上去
    

第10章，运行得不到结果，估计是jibx没有运行
    下载了ANT，但是build.xml中的代码相对位置被调整过，所以还不能运行。需要重新把build.xml进行复制。
    已经成功了服务器端，但是服务端已经发生数据过去了，客户端还没有收到数据。
    看了原因，是执行不到客户端的ChannelRead0处。消息的确是已经客户端。不知道是什么原因
    对比Netty in Action 书中，它采用的是HttpclientCode的方式
    
    网上的大部分是将消息发送独立到外部，不放入到通信通道中。另外，网上的没有对消息体进行xml编码
    
但是因为文件是放入到target目录下的class文件中，所以每次运行其他项目之后，在运行它就不成功，需要重新把简书中的三个步骤全部重新来一篇

已经基本修复好，因为是Netty4.X的一个Bug导致的，simpleinbound方法中有一个方法总是返回false

11章
12章，服务器能启动，客户端无法启动，报buildUnMarshalling的错误
已经解决，就是把POM文件增加了jibx的依赖，就可以正常运行
  
  bug修复：客户端能启动，但是有一个responseDecode在Pipeline中，但是就没有执行到这个类中。非常奇怪。
 
 在12章，增补了采用Json传递的方式，比xml简单，不需要单独对json在外部进行处理。直接在同一个程序进行处理。

 在这里面，采用了心跳检查的自定义的handle。其实，可以采用Netty自己带的参数配置来获得同样的效果。
