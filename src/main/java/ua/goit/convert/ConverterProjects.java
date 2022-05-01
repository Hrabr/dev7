package ua.goit.convert;

import ua.goit.dao.ProjectsDao;
import ua.goit.dto.ProjectsDto;

public class ConverterProjects {
    public ProjectsDao to(ProjectsDto dto) {
        return ProjectsDao.builder().id_projects(dto.getId_projects()).cost_project(dto.getCost_project())
                .name_projects(dto.getName_projects()).start_project(dto.getStart_project())
                .companies_id(dto.getCompanies_id()).customers_id(dto.getCustomers_id()).build();
    }

    public ProjectsDto from(ProjectsDao dao) {
        return ProjectsDto.builder().id_projects(dao.getId_projects()).cost_project(dao.getCost_project())
                .name_projects(dao.getName_projects()).start_project(dao.getStart_project())
                .companies_id(dao.getCompanies_id()).customers_id(dao.getCustomers_id()).build();
    }
}
