# spring-amqp

此為 spring boot 搭配 RabbitMQ 走 RPC模式 , 多個client分別監聽各自的  REPLY Queue , 避免訊息誤收

step:

1.透過docker啟動 RabbitMQ <br>
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management

2.啟動 amqp-server , 並透過 REPLY_TO 動態回傳Queue 

3.run test amqp-client  ApplicationTests

![image](https://github.com/jarvis0325/spring-amqp/blob/master/img/01.JPG)

![image](https://github.com/jarvis0325/spring-amqp/blob/master/img/02.JPG)

![image](https://github.com/jarvis0325/spring-amqp/blob/master/img/03.JPG)
