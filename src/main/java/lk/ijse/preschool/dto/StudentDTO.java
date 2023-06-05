package lk.ijse.preschool.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class StudentDTO {
   private String stId;
   private String name;
   private String address;
   private String DOB;
   private String contact;
   private String parentName;
   private String teachId;


}
