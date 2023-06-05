package lk.ijse.preschool.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class Student implements SuperEntity {
   private String stId;
   private String name;
   private String address;
   private String DOB;
   private String contact;
   private String parentName;
   private String teachId;


}
