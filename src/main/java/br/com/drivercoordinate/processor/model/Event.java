package br.com.drivercoordinate.processor.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
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
}