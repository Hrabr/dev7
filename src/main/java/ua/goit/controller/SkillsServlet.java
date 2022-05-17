package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.base_service.DeveloperBase;
import ua.goit.dao.DeveloperDao;
import ua.goit.dao.SkillDao;
import ua.goit.dto.SkillDto;
import ua.goit.service.DeveloperService;
import ua.goit.service.SkillsService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/skill/*")
public class SkillsServlet extends HttpServlet {
    private SkillsService skillsService;
    private String skills;
    private String delete;

    @Override
    public void init() throws ServletException {
        this.skillsService = (SkillsService) getServletContext().getAttribute("skillsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<SkillDto> all = skillsService.getAll();
        req.setAttribute("Skills", all);
        String userId = req.getParameter("devId");
        String edit = req.getParameter("edit");
        skills = req.getParameter("skills");
        String requestURI = req.getRequestURI();
        if ("/skill/new".equals(requestURI)) {

            req.setAttribute("Id", userId);
            req.getRequestDispatcher("/jsp/new_skill.jsp").forward(req, resp);

        } else if ("/skill/edit".equals(requestURI)) {

            Integer integer = Integer.valueOf(edit);
            for (SkillDto dto : all) {
                if (integer.equals(dto.getId_skill())) {
                    req.setAttribute("skills", skills);
                    req.setAttribute("SkillDto", dto);
                    req.getRequestDispatcher("/jsp/edit_skill.jsp").forward(req, resp);
                }
            }
            req.getRequestDispatcher("/jsp/edit_skill.jsp").forward(req, resp);
        } else if ("/skill/delete".equals(requestURI)) {

            skills = req.getParameter("skills");
            delete = req.getParameter("delete");
            int deletes = Integer.parseInt(delete);
            Optional<SkillDao> skill = skillsService.get(deletes);
            SkillDao skillDao = skill.get();
            skillsService.delete(skillDao);

            req.getRequestDispatcher("/developers?skills=" + skills).forward(req, resp);
        }
        req.getRequestDispatcher("/jsp/skills.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SkillDao build = SkillDao.builder()
                .language(req.getParameter("language"))
                .level_skill(req.getParameter("level"))
                .build();
        String id1 = req.getParameter("Id");
        String back = req.getParameter("back");
        String requestURI = req.getRequestURI();
        if (requestURI.equals("/skill/new")) {

            Integer id = Integer.parseInt(id1);
            DeveloperService developersService = new DeveloperService(new DeveloperBase());
            Optional<DeveloperDao> developersDao = developersService.get(id);
            DeveloperDao dao = developersDao.get();
            skillsService.create(build, dao);
            resp.sendRedirect("/developers?skills=" + id1);

        } else {

            build.setId_skill(Integer.parseInt(req.getParameter("skills")));
            skillsService.updateSkill(build);
            resp.sendRedirect("/developers?skills=" + back);

        }
    }
}

