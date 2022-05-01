package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.dto.SkillDto;
import ua.goit.service.SkillsService;

import java.io.IOException;
import java.util.List;

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
            skillsService.deleteDeveloperSkill(skills, delete);
            skillsService.deleteSkill(delete);
            req.getRequestDispatcher("/developers?skills=" + skills).forward(req, resp);
        }
        req.getRequestDispatcher("/jsp/skills.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDto build = SkillDto.builder().language(req.getParameter("language"))
                .level_skill(req.getParameter("level")).build();
        String id1 = req.getParameter("Id");
        String requestURI = req.getRequestURI();
        if (requestURI.equals("/skill/new")) {
            Integer id = Integer.parseInt(id1);
            Integer save = skillsService.save(build);
            int saveId = skillsService.saveDevelopersSkill(id, save);
            if (save != null && saveId != 0) {
                req.setAttribute("Save", "Skill saved");
                req.setAttribute("In", id1);
            } else {
                req.setAttribute("Save", "Skill not save");
            }
            req.getRequestDispatcher("/jsp/new_skill.jsp").forward(req, resp);
        } else {
            build.setId_skill(Integer.parseInt(req.getParameter("skills")));
            int update = skillsService.update(build);
            if (update != 0) {
                SkillDto skillDto = skillsService.get(update);
                req.setAttribute("SkillDto", skillDto);
                req.setAttribute("skills", skills);
                req.setAttribute("Save", "Skill edited");
            } else {
                req.setAttribute("Save", "Skill not edited");
            }
            req.getRequestDispatcher("/jsp/edit_skill.jsp").forward(req, resp);
        }
    }
}

