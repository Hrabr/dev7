package ua.goit.service;


import ua.goit.base_service.ProjectBase;
import ua.goit.convert.ConverterProjects;
import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectService {
    private final ProjectBase projectsBase;

    public ProjectService(ProjectBase projectsBase) {
        this.projectsBase = projectsBase;
    }

    public List<ProjectDto> getAll() {
        ConverterProjects converterProjects = new ConverterProjects();
        List<ProjectDao> all = projectsBase.getAll();
        return all.stream()
                .map(p -> converterProjects.from(p))
                .collect(Collectors.toList());

    }

    public Optional<ProjectDao> get(int id) {
        return projectsBase.get(id);
    }

    public void update(ProjectDao projects) {
        projectsBase.update(projects);
    }

    public void create(ProjectDao projects, DeveloperDao developers) {

        List<DeveloperDao> developersDao = new ArrayList<>();
        developersDao.add(developers);
        projects.setDevelopers(developersDao);
        projectsBase.create(projects);
    }

    public void delete(ProjectDao projects) {
        projectsBase.delete(projects);
    }

    public List<ProjectDto> getProjectsWithNullableCompany() {

        ConverterProjects converterProjects = new ConverterProjects();
        List<ProjectDao> projectsWithNullableCompany = projectsBase.getProjectsWithNullableCompany();
        return projectsWithNullableCompany.stream()
                .map(c -> converterProjects.from(c))
                .collect(Collectors.toList());
    }

    public List<ProjectDto> getProjectsWithNullableCustomer() {

        ConverterProjects converterProjects = new ConverterProjects();
        List<ProjectDao> projectsWithNullableCustomer = projectsBase.getProjectsWithNullableCustomer();
        return projectsWithNullableCustomer.stream()
                .map(c -> converterProjects.from(c))
                .collect(Collectors.toList());
    }

    public void updateProject(ProjectDao dao) {

        Optional<ProjectDao> project = projectsBase.get(dao.getId_projects());
        ProjectDao projectDao = project.get();
        projectDao.setCost_project(dao.getCost_project());
        projectDao.setName_projects(dao.getName_projects());
        projectDao.setStart_project(dao.getStart_project());
        projectsBase.update(projectDao);
    }
}
