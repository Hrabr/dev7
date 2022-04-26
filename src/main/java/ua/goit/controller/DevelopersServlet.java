package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ua.goit.dao.DevelopersDao;
import ua.goit.dto.DevelopersDto;
import ua.goit.service.DevelopersService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/developers/*")
public class DevelopersServlet extends HttpServlet {
    private DevelopersService developersService;
      String skills;
    @Override
    public void init() throws ServletException {
        this.developersService = (DevelopersService) getServletContext().getAttribute("developersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DevelopersDto> all = developersService.getAll();
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

            int i = Integer.parseInt(skills);
            for (DevelopersDto list : all) {
                if (i == list.getId_developers()) {
                    req.setAttribute("i",i);
                    req.setAttribute("Skills", list.getSkillDto());
                    req.setAttribute("Name", list);
                    req.setAttribute("Back", "developers");
                    req.getRequestDispatcher("/jsp/skills.jsp").forward(req, resp);
                }
            }
        } else if (projects != null) {
            int i = Integer.parseInt(projects);
            for (DevelopersDto list : all) {
                if (i == list.getId_developers()) {
                    req.setAttribute("i",i);
                    req.setAttribute("Projects", list.getProjectsDto());
                    req.setAttribute("Name", list);
                    req.setAttribute("Back", "developers");
                    req.getRequestDispatcher("/jsp/projects.jsp").forward(req, resp);
                }
            }
        } else if (Edit != null) {
            int i = Integer.parseInt(Edit);
            for (DevelopersDto list : all) {
                if (i == list.getId_developers()) {
                    req.setAttribute("Developers", list);
                    req.getRequestDispatcher("/jsp/edit_developers.jsp").forward(req, resp);
                }
            }
        }else if(contextPath.equals("/developers/remove")){
developersService.deleteDeveloper(remove);
            List<DevelopersDto> all1 = developersService.getAll();
            req.setAttribute("developers", all1);
req.getRequestDispatcher("/jsp/developers.jsp").forward(req,resp);
        }
    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DevelopersDao developersDao = DevelopersDao.builder()
                .name(req.getParameter("Name"))
                .age(Integer.parseInt(req.getParameter("Age"))).gender(req.getParameter("Gender"))
                .salary(new BigDecimal(req.getParameter("Salary"))).build();
        String requestURI = req.getRequestURI();
        if (requestURI.equals("/developers/New")) {

            Integer save = developersService.save(developersDao);
            if (save != null) {
                req.setAttribute("Save", "Developer saved");
            } else {
                req.setAttribute("Save", "Developer not save");
            }
            req.getRequestDispatcher("/jsp/new_developers.jsp").forward(req, resp);
        }else{
            developersDao.setId_developers(Integer.parseInt(req.getParameter("developersId")));
            int update = developersService.update(developersDao);
            if (update != 0) {
                DevelopersDto dto = developersService.get(update);
                req.setAttribute("Developers", dto);
                req.setAttribute("Save", "Developer update");
            } else {
                req.setAttribute("Save", "Developer not save");
            }
            req.getRequestDispatcher("/jsp/edit_developers.jsp").forward(req, resp);
        }
    }
}
