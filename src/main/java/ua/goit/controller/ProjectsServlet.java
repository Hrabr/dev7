package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.base_service.DeveloperBase;
import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dto.ProjectDto;
import ua.goit.service.DeveloperService;
import ua.goit.service.ProjectService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/project/*")
public class ProjectsServlet extends HttpServlet {

    private ProjectService projectsService;
    private String project;
    private String delete;

    @Override
    public void init() throws ServletException {
        this.projectsService = (ProjectService) getServletContext().getAttribute("projectsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProjectDto> all = projectsService.getAll();
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
            for (ProjectDto dto : all) {
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
            int projects = Integer.parseInt(delete);
            Optional<ProjectDao> projectDao = projectsService.get(projects);
            ProjectDao projectDao1 = projectDao.get();
            projectsService.delete(projectDao1);

            req.getRequestDispatcher("/developers?projects=" + this.project).forward(req, resp);
        }
        req.getRequestDispatcher("/jsp/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDao build = ProjectDao.builder()
                .name_projects(req.getParameter("Name"))
                .start_project(LocalDate.parse(req.getParameter("Start_project")))
                .cost_project(new BigDecimal(req.getParameter("Cost_project")))
                .build();

        String id1 = req.getParameter("Id");
        String back = req.getParameter("In");
        String requestURI = req.getRequestURI();
        if (requestURI.equals("/project/new")) {

            Integer id = Integer.parseInt(id1);
            DeveloperService developersService = new DeveloperService(new DeveloperBase());
            Optional<DeveloperDao> developersDao = developersService.get(id);
            DeveloperDao dao = developersDao.get();
            projectsService.create(build, dao);
            resp.sendRedirect("/developers?projects=" + id1);

        } else {

            build.setId_projects(Integer.parseInt(req.getParameter("project")));
            projectsService.updateProject(build);

            resp.sendRedirect("/developers?projects="+back);

        }
    }
}

