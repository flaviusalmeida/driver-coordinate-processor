package br.com.drivercoordinate.processor.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document
@RequiredArgsConstructor
public class Coordinate {
    @Id
    private String id;
    @NonNull
    private Long latitude;
    @NonNull
    private Long longitude;
}
