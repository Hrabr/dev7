package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.dao.DeveloperDao;
import ua.goit.dto.DeveloperDto;
import ua.goit.service.DeveloperService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@WebServlet("/developers/*")
public class DevelopersServlet extends HttpServlet {
    private DeveloperService developersService;
    String skills;

    @Override
    public void init() throws ServletException {
        this.developersService = (DeveloperService) getServletContext().getAttribute("developersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<DeveloperDto> all = developersService.getAll();
        req.setAttribute("developers", all);
        skills = req.getParameter("skills");
        String projects = req.getParameter("projects");
        String Edit = req.getParameter("Edit");
        String remove = req.getParameter("Remove");
        String contextPath = req.getRequestURI();

        if (contextPath.equals("/developers/New")) {

            req.getRequestDispatcher("/jsp/new_developers.jsp").forward(req, resp);

        } else if (skills == null && projects == null && Edit == null && remove == null) {

            req.getRequestDispatcher("/jsp/developers.jsp").forward(req, resp);

        } else if (skills != null) {

            int skillsId = Integer.parseInt(skills);
            for (DeveloperDto list : all) {
                if (skillsId == list.getId_developers()) {
                    req.setAttribute("skillsId", skillsId);
                    req.setAttribute("Skills", list.getSkillDto());
                    req.setAttribute("Name", list);
                    req.setAttribute("Back", "developers");
                    req.getRequestDispatcher("/jsp/skills.jsp").forward(req, resp);
                }
            }
        } else if (projects != null) {
            int projectId = Integer.parseInt(projects);
            for (DeveloperDto list : all) {
                if (projectId == list.getId_developers()) {
                    req.setAttribute("projectId", projectId);
                    req.setAttribute("Projects", list.getProjectsDto());
                    req.setAttribute("Name", list);
                    req.setAttribute("Back", "developers");
                    req.getRequestDispatcher("/jsp/projects.jsp").forward(req, resp);
                }
            }
        } else if (Edit != null) {
            int editId = Integer.parseInt(Edit);
            for (DeveloperDto list : all) {
                if (editId == list.getId_developers()) {
                    req.setAttribute("Developers", list);
                    req.getRequestDispatcher("/jsp/edit_developers.jsp").forward(req, resp);
                }
            }
        } else if (contextPath.equals("/developers/remove")) {

            final Optional<DeveloperDao> developersDao = developersService.get(Integer.parseInt(remove));
            developersDao.ifPresent(dao -> developersService.delete(dao));
            List<DeveloperDto> allDeveloperDto = developersService.getAll();
            req.setAttribute("developers", allDeveloperDto);
            req.getRequestDispatcher("/jsp/developers.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDao developersDao = DeveloperDao.builder()
                .name(req.getParameter("Name"))
                .age(Integer.parseInt(req.getParameter("Age"))).gender(req.getParameter("Gender"))
                .salary(new BigDecimal(req.getParameter("Salary")))
                .build();

        String requestURI = req.getRequestURI();
        if (requestURI.equals("/developers/New")) {

            developersService.create(developersDao);

        } else {

            developersDao.setId_developers(Integer.parseInt(req.getParameter("developersId")));
            developersService.updateDevelopers(developersDao);
        }
        resp.sendRedirect("/developers");

    }
}
