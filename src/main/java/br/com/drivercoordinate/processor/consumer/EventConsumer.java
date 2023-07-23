package br.com.drivercoordinate.processor.consumer;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import br.com.drivercoordinate.processor.model.Event;
import br.com.drivercoordinate.processor.service.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private final EventService service;

    public EventConsumer(EventService service) {
        this.service = service;
    }

    @RabbitListener(queues = {"${events.queue}"})
    public void receive(DriverInfoDto message) {
        System.out.println("Message: " + message);
        Event event = new Event();
        event.setVehiclePlate(message.getVehiclePlate());
        service.save(event);

    }

}
