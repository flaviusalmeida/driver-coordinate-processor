package br.com.drivercoordinate.processor.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageTestDTO {

    private int id = 1;
    private String message = "Test message RabbitMQ";

}
