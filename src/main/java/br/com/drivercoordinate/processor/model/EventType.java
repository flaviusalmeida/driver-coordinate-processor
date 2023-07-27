package br.com.drivercoordinate.processor.model;

import lombok.Getter;

@Getter
public enum EventType {

    SPEEDING(101),
    OVER_TEMPERATURE(101),
    LOW_BATTERY(101),
    HARD_ACCELERATION(104),
    HARD_BRAKING(105),
    HARD_TURNING(106);

    private Integer transmissionReasonId;

    EventType(Integer transmissionReasonId) {
        this.transmissionReasonId = transmissionReasonId;
    }

    public static EventType fromValue(Integer transmissionReasonId) {
        for (EventType eventType : EventType.values()) {
            if (eventType.transmissionReasonId.equals(transmissionReasonId)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Invalid transmissionReasonId: " + transmissionReasonId);
    }
}