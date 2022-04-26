package ua.goit.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Setter
@Getter
public class DevelopersDao {
    private int id_developers;
    private String name;
    private int age;
    private String gender;
    private BigDecimal salary;
    private List<SkillDao> skillDao;
    private List<ProjectsDao> projectsDao;

}
