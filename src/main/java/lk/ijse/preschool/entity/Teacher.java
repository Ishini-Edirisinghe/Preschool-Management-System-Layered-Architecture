package lk.ijse.preschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Teacher implements SuperEntity {
   private String teachId;
   private String name;
   private String address;
   private String DOB;
   private String contact;

}
