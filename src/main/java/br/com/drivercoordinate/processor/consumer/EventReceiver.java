package br.com.drivercoordinate.processor.consumer;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import br.com.drivercoordinate.processor.service.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class EventReceiver {

    private final EventService service;

    public EventReceiver(EventService service) {
        this.service = service;
    }

    @RabbitListener(queues = {"${events.queue}"})
    public void receive(DriverInfoDto driverInfo) {
        service.processEvents(driverInfo);
    }


}
