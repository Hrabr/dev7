package ua.goit.convert;

import ua.goit.dao.ProjectDao;
import ua.goit.dto.ProjectDto;

public class ConverterProjects {

    public ProjectDao to(ProjectDto dto) {
        return ProjectDao.builder()
                .id_projects(dto.getId_projects())
                .cost_project(dto.getCost_project())
                .name_projects(dto.getName_projects())
                .start_project(dto.getStart_project())
                .build();
    }

    public ProjectDto from(ProjectDao dao) {
        return ProjectDto.builder()
                .id_projects(dao.getId_projects())
                .cost_project(dao.getCost_project())
                .name_projects(dao.getName_projects())
                .start_project(dao.getStart_project())
                .build();
    }
}
