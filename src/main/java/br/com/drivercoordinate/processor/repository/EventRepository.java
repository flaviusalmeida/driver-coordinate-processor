package br.com.drivercoordinate.processor.repository;

import br.com.drivercoordinate.processor.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {

    @Query("{'vehiclePlate' : ?0, 'closedDate': null}")
    List<Event> findOpenedEventsByVehicle(String vehiclePlate);

}
