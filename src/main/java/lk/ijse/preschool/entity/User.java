package lk.ijse.preschool.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User implements SuperEntity{
    private String id;
    private String user_name;
    private String password;

}
