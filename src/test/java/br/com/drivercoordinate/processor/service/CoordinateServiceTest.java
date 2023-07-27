package br.com.drivercoordinate.processor.service;

import org.springframework.boot.test.context.SpringBootTest;

import br.com.drivercoordinate.processor.model.Coordinate;
import br.com.drivercoordinate.processor.repository.CoordinateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CoordinateServiceTest {

    @Mock
    private CoordinateRepository coordinateRepository;

    @InjectMocks
    private CoordinateService coordinateService;

    private Coordinate testCoordinate;

    @BeforeEach
    public void setUp() {
        testCoordinate = new Coordinate();
        testCoordinate.setId("123");
        testCoordinate.setLatitude((long) 40.7128);
        testCoordinate.setLongitude((long) -74.0060);
    }

    @Test
    public void testSaveCoordinate() {
        when(coordinateRepository.save(testCoordinate)).thenReturn(testCoordinate);

        Coordinate savedCoordinate = coordinateService.save(testCoordinate);

        assertEquals(testCoordinate.getId(), savedCoordinate.getId());
        assertEquals(testCoordinate.getLatitude(), savedCoordinate.getLatitude());
        assertEquals(testCoordinate.getLongitude(), savedCoordinate.getLongitude());
        verify(coordinateRepository).save(testCoordinate);
    }
}
