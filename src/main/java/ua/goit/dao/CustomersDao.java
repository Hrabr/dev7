package ua.goit.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class CustomersDao {
    private int id_customers;
    private String name_customers;
    private String country_customers;
    private List<ProjectsDao> projectsDao;

}
