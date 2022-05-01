package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.dto.ProjectsDto;
import ua.goit.service.ProjectsService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/project/*")
public class ProjectsServlet extends HttpServlet {

    private ProjectsService projectsService;
    private String project;
    private String delete;

    @Override
    public void init() throws ServletException {
        this.projectsService = (ProjectsService) getServletContext().getAttribute("projectsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProjectsDto> all = projectsService.getall();
        req.setAttribute("Projects", all);
        String userId = req.getParameter("devId");
        String edit = req.getParameter("edit");
        project = req.getParameter("project");
        String requestURI = req.getRequestURI();
        if ("/project/new".equals((requestURI))) {
            req.setAttribute("Id", userId);
            req.getRequestDispatcher("/jsp/new_project.jsp").forward(req, resp);
        } else if ("/project/edit".equals(requestURI)) {
            Integer integer = Integer.valueOf(edit);
            for (ProjectsDto dto : all) {
                if (integer.equals(dto.getId_projects())) {
                    req.setAttribute("proj", project);
                    req.setAttribute("ProjectDto", dto);
                    req.getRequestDispatcher("/jsp/edit_project.jsp").forward(req, resp);
                }
            }
            req.getRequestDispatcher("/jsp/edit_project").forward(req, resp);
        } else if ("/project/delete".equals(requestURI)) {
            project = req.getParameter("project");
            delete = req.getParameter("delete");
            projectsService.deleteDeveloperProject(project, delete);
            projectsService.deleteProject(delete);
            req.getRequestDispatcher("/developers?projects=" + project).forward(req, resp);
        }
        req.getRequestDispatcher("/jsp/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectsDto build = ProjectsDto.builder().name_projects(req.getParameter("Name")).start_project(req.getParameter("Start_project"))
                .cost_project(new BigDecimal(req.getParameter("Cost_project"))).build();
        String id1 = req.getParameter("Id");
        String requestURI = req.getRequestURI();
        if (requestURI.equals("/project/new")) {
            Integer id = Integer.parseInt(id1);
            Integer save = projectsService.save(build);
            int i = projectsService.saveDevelopersProject(id, save);
            if (save != null && i != 0) {
                req.setAttribute("Save", "Project saved");
                req.setAttribute("In", id1);
            } else {
                req.setAttribute("Save", "Project not save");
            }
            req.getRequestDispatcher("/jsp/new_project.jsp").forward(req, resp);
        } else {
            build.setId_projects(Integer.parseInt(req.getParameter("project")));
            Integer update = projectsService.update(build);
            if (update != 0) {
                ProjectsDto projectsDto = projectsService.get(update);
                req.setAttribute("ProjectDto", projectsDto);
                req.setAttribute("proj", project);
                req.setAttribute("Save", "Project edited");
            } else {
                req.setAttribute("Save", "Project not edited");
            }
            req.getRequestDispatcher("/jsp/edit_project.jsp").forward(req, resp);
        }
    }
}

