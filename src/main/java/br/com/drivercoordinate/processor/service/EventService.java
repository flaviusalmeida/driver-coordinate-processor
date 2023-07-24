package br.com.drivercoordinate.processor.service;

import br.com.drivercoordinate.processor.model.Event;
import br.com.drivercoordinate.processor.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public void save(Event event) {
        repository.save(event);
    }

    public List<Event> findOpenedEventsByVehicle(Event event) {
        return repository.findOpenedEventsByVehicle(event.getVehiclePlate());
    }
}
