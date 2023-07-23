package br.com.drivercoordinate.processor.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverInfoDto {
    private String vehiclePlate;
    private String coordinateDate;
    private long latitude;
    private long longitude;
    private long hodometer;
    private double batteryVoltage;
    private double speed;
    private double acceleration;
    private int rpm;
    private boolean ignition;
    private double temperature;
    private int packetCounter;
    private int transmissionReasonId;
}