package ua.goit.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDto {
    private int id_developers;
    private String name;
    private int age;
    private String gender;
    private BigDecimal salary;
    private List<SkillDto> skillDto;
    private List<ProjectDto> projectsDto;


}
