package ua.goit.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProjectsDao {
    private int id_projects;
    private String name_projects;
    private BigDecimal cost_project;
    private String start_project;
    private int companies_id;
    private int customers_id;

}
