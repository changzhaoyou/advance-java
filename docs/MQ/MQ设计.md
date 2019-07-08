# **ONS设计思路**
##一、**消息中间件设计目的**
 - 解耦
 - 异步
 - 最终一致性
 - 并行
##二、**消息中间件设计思路和关键概念**
###**（一）、设计假定**
 - 每台PC机器都可能down机不可服务
 - 任意集群都可能处理能力不足
 - 最坏事情一定会发生
 - 内网环境需要低延迟来提供最佳用户体验
###**（二）、关键概念**
 - **分布式集群化**（每个地方要可以高扩展和收缩）
 ![1.png-225.6kB][1]


 - **强数据安全**
 ![image.png-196.9kB][2]


 - **海量数据堆积**
 ![image.png-124.4kB][3]


![image.png-103.3kB][4]


###**（三）、毫秒级延迟**
 1. **Pull模式**
    拉模式是客户端主动从服务器端获取信息
        **拉模式的主要优点**
            （1）针对性强，能满足客户端的个性化需求
            （2）信息传输量较小，网络中传输的知识客户端的请求和服务器端对该请求的响应
            （3）服务器端的任务轻。服务器端只是被动接收查询，对客户端的查询请求做出响应
        **拉模式的缺点**
            （1）实时较差，针对于服务器端实时更新的信息，客户端难以获取实时信息
            （2）对于客户端用户的要求较高，需要对服务器端具有一定的了解。
 2. **Push模式**
    推模式是服务器端根据用户需要，由目的、按时将用户感兴趣的信息主动发送到用户的客户端
       **Push模式的主要优点：**
            （1）对用户要求低，方便用户获取需要的信息
            （2）及时性好，服务器端即使地向客户端推送更行的动态信息
       **Push模式的缺点**
            （1）不能确保发送成功。Push模式采用广播方式，只有服务器端和客户端在同一个频道上，推模式才有效，用户才能接收到信息。
            （2）没有信息状态跟踪。Push模式采用开环控制技术，一个信息推送后的状态，比如客户端是否接收等，无从得知。
            （3）针对性较差。推送的信息可能并不能满足客户端的个性化需求。
 3. **推拉结合使用**
      （1） client主动向server定时轮询消息，发消息到server时，如果有消息立即返回。如果server没有消息的情况，则在server中等待
      （2）消息来后，push消息到client，这样做到毫秒鸡投递延迟。
###**（四）、消息乱序**
![image.png-71kB][5]
![image.png-94.1kB][6]
![image.png-84.3kB][7]
###**（五）、消息重复**
      客户端解决自行解决重复问题

  [1]: http://static.zybuluo.com/yzz19881016/r5rmmvu4w66zd3e2y8ac5cg0/1.png
  [2]: http://static.zybuluo.com/yzz19881016/4co5zrgv0ns4ed08xfwnq5w4/image.png
  [3]: http://static.zybuluo.com/yzz19881016/ujoxx7k0xjvif90iconiq7y9/image.png
  [4]: http://static.zybuluo.com/yzz19881016/wggckdm7wcxdszaki3ck10iy/image.png
  [5]: http://static.zybuluo.com/yzz19881016/0at4b30sbqswswfvmd17caqz/image.png
  [6]: http://static.zybuluo.com/yzz19881016/iz1py6jionwa007jd37b0yxu/image.png
  [7]: http://static.zybuluo.com/yzz19881016/pb4e0cmf510mz3hu18kr9w9b/image.png