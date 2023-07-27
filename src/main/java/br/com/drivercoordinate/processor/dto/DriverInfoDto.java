package br.com.drivercoordinate.processor.dto;

import static br.com.drivercoordinate.processor.model.EventType.HARD_ACCELERATION;
import static br.com.drivercoordinate.processor.model.EventType.HARD_BRAKING;
import static br.com.drivercoordinate.processor.model.EventType.HARD_TURNING;

import br.com.drivercoordinate.processor.model.EventType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public boolean isHardAcceleration() {
        return transmissionReasonId == HARD_ACCELERATION.getTransmissionReasonId() && acceleration > 0.22;
    }

    public boolean isHardBraking() {
        return transmissionReasonId == HARD_BRAKING.getTransmissionReasonId() && acceleration > 0.17;
    }

    public boolean isHardTurning() {
        return transmissionReasonId == HARD_TURNING.getTransmissionReasonId() && acceleration > 0.30;
    }

    public boolean isSpeeding() {
        return speed > 80;
    }

    public boolean isOverTemperature() {
        return temperature > 115;
    }

    public boolean isLowBattery() {
        return batteryVoltage < 21;
    }
}