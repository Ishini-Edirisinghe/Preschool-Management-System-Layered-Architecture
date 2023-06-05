package lk.ijse.preschool.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class SkillStatusDTO {
    private String stid;
    private String stName;
    private String counting ;
    private String crafting ;
    private String drawing  ;
    private String reading  ;
    private String singing  ;
    private String writing  ;

}
