package lk.ijse.preschool.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class Payment {
    private String ref_no;
    private String date;
    private String stid;
    private String type;
}
