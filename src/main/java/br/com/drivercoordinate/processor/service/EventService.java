package br.com.drivercoordinate.processor.service;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import br.com.drivercoordinate.processor.model.Event;
import br.com.drivercoordinate.processor.model.EventType;
import br.com.drivercoordinate.processor.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EventService {

    private final EventRepository repository;

    private final ModelMapper mapper;

    public EventService(EventRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(Event event) {
        repository.save(event);
    }

    public List<Event> findOpenedEventsByVehicle(String vehiclePlate) {
        return repository.findOpenedEventsByVehicle(vehiclePlate);
    }

    public void processEvents(DriverInfoDto driverInfo) {
        List<Event> events = findOpenedEventsByVehicle(driverInfo.getVehiclePlate());
        Stream<EventType> eventTypes = Arrays.stream(EventType.values());
        eventTypes.forEach(eventType -> saveEventWhenNecessary(events, driverInfo, eventType));
    }

    public void saveEventWhenNecessary(List<Event> events, DriverInfoDto driverInfo, EventType eventType) {
        Event event = findEventByEventType(events, eventType);
        boolean condition = getConditionByEventType(driverInfo, eventType);

        if (event == null && condition) {
            createEvent(driverInfo, eventType);
        }
        if (event != null && !condition) {
            event.setClosedDate(LocalDateTime.now().toString());
            save(event);
        }
    }

    private Event findEventByEventType(List<Event> events, EventType eventType) {
        Optional<Event> event = events.stream()
                .filter(e -> e.getEventType() == eventType)
                .findFirst();
        if (event.isPresent()) {
            return event.get();
        }
        return null;
    }

    private boolean getConditionByEventType(DriverInfoDto driverInfo, EventType eventType) {
        return switch (eventType) {
            case HARD_ACCELERATION -> driverInfo.isHardAcceleration();
            case HARD_BRAKING -> driverInfo.isHardBraking();
            case HARD_TURNING -> driverInfo.isHardTurning();
            case SPEEDING -> driverInfo.isSpeeding();
            case OVER_TEMPERATURE -> driverInfo.isOverTemperature();
            case LOW_BATTERY -> driverInfo.isLowBattery();
        };
    }

    public void createEvent(DriverInfoDto driverInfo, EventType eventType) {

        Event event = mapper.map(driverInfo, Event.class);
        event.setEventType(eventType);
        event.setOpenedDate(LocalDateTime.now().toString());
        save(event);
    }
}
