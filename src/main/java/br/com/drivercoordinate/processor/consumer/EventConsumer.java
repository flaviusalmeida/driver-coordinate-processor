package br.com.drivercoordinate.processor.consumer;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @RabbitListener(queues = {"${events.queue}"})
    public void receive(DriverInfoDto message) {
         System.out.println("Message: " + message);
    }

}
