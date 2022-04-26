package ua.goit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Setter
@Getter
public class DevelopersDto {
    private int id_developers;
    private String name;
    private int age;
    private String gender;
    private BigDecimal salary;
    private List<SkillDto> skillDto;
    private List<ProjectsDto> projectsDto;


}
