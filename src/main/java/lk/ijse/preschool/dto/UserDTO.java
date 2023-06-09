package lk.ijse.preschool.dto;


import lk.ijse.preschool.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDTO  {
    private String id;
    private String user_name;
    private String password;

}
