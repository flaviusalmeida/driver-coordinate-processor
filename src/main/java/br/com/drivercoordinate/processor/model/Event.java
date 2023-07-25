package br.com.drivercoordinate.processor.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event {
    @Id
    private String id;
    private String vehiclePlate;
    private String openedDate;
    private String closedDate;
    private long latitude;
    private long longitude;
    private EventType eventType;
    private double speed;

    @Override
    public Event clone() {
        try {
            return (Event) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
