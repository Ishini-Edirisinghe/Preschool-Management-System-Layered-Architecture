package lk.ijse.preschool.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data


public class Syllabus implements SuperEntity {
    private String subject_id;
    private String sub_name;
    //private String stid;
}
