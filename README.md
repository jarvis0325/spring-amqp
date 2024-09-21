# spring-amqp

此為 spring boot 搭配 RabbitMQ 走 RPC模式 

step:

1.透過docker啟動 RabbitMQ
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management

2. 啟動 amqp-server , 並透過 REPLY_TO 動態回傳Queue

3. run test amqp-client  ApplicationTests