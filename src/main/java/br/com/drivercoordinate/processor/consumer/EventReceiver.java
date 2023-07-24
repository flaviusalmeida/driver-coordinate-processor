package br.com.drivercoordinate.processor.consumer;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import br.com.drivercoordinate.processor.model.Event;
import br.com.drivercoordinate.processor.model.EventType;
import br.com.drivercoordinate.processor.service.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

@Component
public class EventReceiver {

    private final EventService service;

    private final ModelMapper mapper;

    public EventReceiver(EventService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RabbitListener(queues = {"${events.queue}"})
    public void receive(DriverInfoDto driverInfo) {
        Event e = mapper.map(driverInfo, Event.class);
//        event.setOpenedDate(LocalDateTime.now().toString());
//        System.out.println(driverInfo);
//        System.out.println(event);
//        service.save(event);

        List<Event> events = service.findOpenedEventsByVehicle(e);

        if (driverInfo.getTransmissionReasonId() == 104 && driverInfo.getAcceleration() > 0.22) {
            saveEventWhenNecessary(e, events, EventType.HARD_ACCELERATION);
        }
        if (driverInfo.getTransmissionReasonId() == 105 && driverInfo.getAcceleration() > 0.17) {
            saveEventWhenNecessary(e, events, EventType.HARD_BRAKING);
        }
        if (driverInfo.getTransmissionReasonId() == 106 && driverInfo.getAcceleration() > 0.30) {
            saveEventWhenNecessary(e, events, EventType.HARD_TURNING);
        }
        if (driverInfo.getSpeed() > 80) {
            saveEventWhenNecessary(e, events, EventType.SPEEDING);
        }
        if (driverInfo.getTemperature() > 115) {
            saveEventWhenNecessary(e, events, EventType.OVER_TEMPERATURE);
        }
        if (driverInfo.getBatteryVoltage() < 21) {
            saveEventWhenNecessary(e, events, EventType.LOW_BATTERY);
        }

    }

    private Event findEventByEventType(List<Event> events, EventType eventType) {
        Optional<Event> firstElement = events.stream()
                .filter(e -> e.getEventType() == eventType)
                .findFirst();
        if (firstElement.isPresent())
            return firstElement.get();
        return null;
    }

    private void saveEventWhenNecessary(Event event, List<Event> events, EventType eventType) {
        boolean hasEvent = events.stream()
                .anyMatch(e -> e.getEventType() == eventType);

        if (!hasEvent) {
            Event newEvent = event.clone();
            newEvent.setEventType(eventType);

            service.save(event);
        }
    }

}
