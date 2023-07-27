package br.com.drivercoordinate.processor.consumer;

import br.com.drivercoordinate.processor.dto.DriverInfoDto;
import br.com.drivercoordinate.processor.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class EventReceiverTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventReceiver eventReceiver;

    @Test
    public void testReceive() {
        DriverInfoDto driverInfo = new DriverInfoDto();
        eventReceiver.receive(driverInfo);
        verify(eventService, times(1)).processEvents(driverInfo);
    }
}