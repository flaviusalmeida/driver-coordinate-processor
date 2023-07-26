package br.com.drivercoordinate.processor.repository;

import br.com.drivercoordinate.processor.model.Coordinate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoordinateRepository extends MongoRepository<Coordinate, String> {

}
