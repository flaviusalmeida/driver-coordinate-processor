package br.com.drivercoordinate.processor.repository;

import br.com.drivercoordinate.processor.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
