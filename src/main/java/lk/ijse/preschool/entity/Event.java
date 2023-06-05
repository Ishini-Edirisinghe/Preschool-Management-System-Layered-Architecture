package lk.ijse.preschool.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class Event implements SuperEntity {
    private String event_no;
    private String name;
    private String month;
}
