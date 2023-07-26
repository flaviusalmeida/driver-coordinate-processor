package br.com.drivercoordinate.processor.service;

import br.com.drivercoordinate.processor.model.Coordinate;
import br.com.drivercoordinate.processor.repository.CoordinateRepository;
import org.springframework.stereotype.Service;

@Service
public class CoordinateService {

    private final CoordinateRepository repository;

    public CoordinateService(CoordinateRepository repository) {
        this.repository = repository;
    }

    public Coordinate save(Coordinate coordinate) {
        return repository.save(coordinate);
    }

}
