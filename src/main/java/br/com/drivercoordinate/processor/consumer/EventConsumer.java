package br.com.drivercoordinate.processor.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message: " + fileBody);
    }

}
