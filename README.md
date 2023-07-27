# Driver's coordinate

Real-time data processing application, using the concept of message broker, for data processing.

## 🚀 Getting started

### 📋 Prerequisites

What things do you need to install the software and how to install it?

```
Java 17
MongoDB
RabbitMQ
Docker
```

## 📦 Deployment

After installing and configuring all the requirements follow the steps:

1. run the command: ```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management```
2. Launch Eureka [![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/fagnerpsantos)](https://github.com/flaviusalmeida/driver-coordinate-eureka)
3. Launch the API Gateway [![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/fagnerpsantos)](https://github.com/flaviusalmeida/driver-coordinate-gateway)
4. Launch the Producer [![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/fagnerpsantos)](https://github.com/flaviusalmeida/driver-coordinate-producer)
5. Launch the Processor [![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/fagnerpsantos)](https://github.com/flaviusalmeida/driver-coordinate-processor)
