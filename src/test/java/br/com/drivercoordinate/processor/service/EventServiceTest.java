package br.com.drivercoordinate.processor.service;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import br.com.drivercoordinate.processor.model.Coordinate;
import br.com.drivercoordinate.processor.model.Event;
import br.com.drivercoordinate.processor.model.EventType;
import br.com.drivercoordinate.processor.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.lang.model.element.TypeElement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.drivercoordinate.processor.model.EventType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CoordinateService coordinateService;

    @Mock
    private ModelMapper mapper;

    @Spy
    @InjectMocks
    private EventService eventService;

    private final String VEHICLE_PLATE = "ABC123";
    private final double SPEED = 80.0;
    private final double HIGH_SPEED = 100.0;
    private final double VOLTAGE = 21.0;
    private final double LOW_VOLTAGE = 12.0;
    private final double TEMPERATURE = 115.0;
    private final double HIGH_TEMPERATURE = 200.0;
    private final double ACCELERATION = 0.22;
    private final double HARD_ACCELERATION = 0.25;
    private final double HARD_BRAKING = 0.20;
    private final double HARD_TURNING = 0.35;

    private DriverInfoDto driverInfo;
    private Event event;

    @BeforeEach
    public void setUp() {
        driverInfo = new DriverInfoDto();
        driverInfo.setBatteryVoltage(VOLTAGE);
        driverInfo.setSpeed(SPEED);
        driverInfo.setAcceleration(ACCELERATION);
        driverInfo.setTemperature(TEMPERATURE);

        event = new Event();
        event.setLatitude(34);
        event.setLongitude(55);
    }

    @Test
    public void shouldCreateNewEvent() {
        driverInfo.setVehiclePlate(VEHICLE_PLATE);
        driverInfo.setSpeed(HIGH_SPEED);
        driverInfo.setTransmissionReasonId(SPEEDING.getTransmissionReasonId());

        List<Event> events = new ArrayList<>();
        when(eventRepository.findOpenedEventsByVehicle(driverInfo.getVehiclePlate())).thenReturn(events);
        when(mapper.map(driverInfo, Event.class)).thenReturn(event);

        eventService.processEvents(driverInfo);

        verify(eventService, times(EventType.values().length)).saveEventWhenNecessary(eq(events), eq(driverInfo), any(EventType.class));
        verify(eventService, times(EventType.values().length)).findEventByEventType(eq(events), any(EventType.class));
        verify(eventService, times(EventType.values().length)).getConditionByEventType(eq(driverInfo), any(EventType.class));
        verify(eventService, times(1)).createEvent(eq(driverInfo), eq(SPEEDING));
        verify(eventRepository, times(1)).save(any(Event.class));
        verify(coordinateService, times(1)).save(any(Coordinate.class));
    }

    @Test
    public void shouldNotCreateNewEvent() {
        driverInfo.setVehiclePlate(VEHICLE_PLATE);
        driverInfo.setTransmissionReasonId(101);

        List<Event> events = new ArrayList<>();
        when(eventRepository.findOpenedEventsByVehicle(driverInfo.getVehiclePlate())).thenReturn(events);
        when(mapper.map(driverInfo, Event.class)).thenReturn(event);

        eventService.processEvents(driverInfo);

        verify(eventService, times(EventType.values().length)).saveEventWhenNecessary(eq(events), eq(driverInfo), any(EventType.class));
        verify(eventService, times(EventType.values().length)).findEventByEventType(eq(events), any(EventType.class));
        verify(eventService, times(EventType.values().length)).getConditionByEventType(eq(driverInfo), any(EventType.class));
        verify(eventService, times(0)).createEvent(eq(driverInfo), any(EventType.class));
        verify(eventRepository, times(0)).save(any(Event.class));
        verify(coordinateService, times(0)).save(any(Coordinate.class));
    }

    @Test
    public void shouldCloseEvent() {
        driverInfo.setVehiclePlate(VEHICLE_PLATE);
        driverInfo.setSpeed(SPEED);
        driverInfo.setTransmissionReasonId(SPEEDING.getTransmissionReasonId());

        event.setOpenedDate(LocalDateTime.now().toString());
        event.setVehiclePlate(VEHICLE_PLATE);
        event.setEventType(SPEEDING);
        event.setSpeed(SPEED);

        List<Event> events = new ArrayList<>();
        events.add(event);

        when(eventRepository.findOpenedEventsByVehicle(driverInfo.getVehiclePlate())).thenReturn(events);
        when(mapper.map(driverInfo, Event.class)).thenReturn(event);

        eventService.processEvents(driverInfo);

        verify(eventService, times(EventType.values().length)).saveEventWhenNecessary(eq(events), eq(driverInfo), any(EventType.class));
        verify(eventService, times(EventType.values().length)).findEventByEventType(eq(events), any(EventType.class));
        verify(eventService, times(EventType.values().length)).getConditionByEventType(eq(driverInfo), any(EventType.class));
        verify(eventService, times(0)).createEvent(eq(driverInfo), eq(SPEEDING));
        verify(eventRepository, times(1)).save(any(Event.class));
        verify(coordinateService, times(1)).save(any(Coordinate.class));

    }

    @Test
    public void shouldFindOpenedEventsByVehicle() {
        List<Event> mockEvents = Arrays.asList(new Event(), new Event());
        when(eventRepository.findOpenedEventsByVehicle(anyString())).thenReturn(mockEvents);

        List<Event> openedEvents = eventService.findOpenedEventsByVehicle(VEHICLE_PLATE);

        assertEquals(mockEvents.size(), openedEvents.size());
    }
}