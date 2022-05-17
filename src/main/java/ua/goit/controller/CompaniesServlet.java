package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.base_service.ProjectBase;
import ua.goit.dao.CompanyDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dto.CompanyDto;
import ua.goit.dto.ProjectDto;
import ua.goit.service.CompaniesService;
import ua.goit.service.ProjectService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/companies/*")
public class CompaniesServlet extends HttpServlet {

    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesService = (CompaniesService) getServletContext().getAttribute("companiesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final ProjectService projectsService = new ProjectService(new ProjectBase());
        List<CompanyDto> all = companiesService.getAll();
        req.setAttribute("Companies", all);
        String projects = req.getParameter("projects");
        String requestURI = req.getRequestURI();
        String remove = req.getParameter("remove");
        String edit = req.getParameter("edit");
        if (requestURI.equals("/companies/remove")) {

            String id = req.getParameter("id");
            String back = req.getParameter("back");
            int idProject = Integer.parseInt(id);
            companiesService.updateNullProject(idProject);
            req.getRequestDispatcher("/companies?projects=" + back).forward(req, resp);

        } else if (requestURI.equals("/companies/New")) {

            req.getRequestDispatcher("/jsp/new_companies.jsp").forward(req, resp);

        } else if (requestURI.equals("/companies/Back")) {

            List<ProjectDto> projectsWithNullableCompany = projectsService.getProjectsWithNullableCompany();
            String aNew = req.getParameter("new");
            req.setAttribute("Projects", projectsWithNullableCompany);
            req.setAttribute("Back", aNew);
            req.getRequestDispatcher("/jsp/company_new_projects.jsp").forward(req, resp);

        } else if (projects == null && remove == null && edit == null) {

            req.getRequestDispatcher("/jsp/companies.jsp").forward(req, resp);

        } else if (projects != null && remove == null) {

            int projectBack = Integer.parseInt(projects);
            for (CompanyDto p : all) {
                if (projectBack == p.getId_companies()) {

                    req.setAttribute("Projects", p.getProjectsDto());
                    req.setAttribute("Name", p.getName_companies());
                    req.setAttribute("Back", projectBack);
                    req.getRequestDispatcher("/jsp/с_projects.jsp").forward(req, resp);
                }
            }
        } else if (remove != null) {

            int removeCompany = Integer.parseInt(remove);
            Optional<CompanyDao> companyDao = companiesService.get(removeCompany);
            CompanyDao companyDao1 = companyDao.get();
            companiesService.delete(companyDao1);
            resp.sendRedirect("/companies");

        } else {

            for (CompanyDto dto : all) {
                int integer = Integer.parseInt(edit);
                if (integer == dto.getId_companies()) {
                    req.setAttribute("company", dto);
                    req.getRequestDispatcher("/jsp/edit_companies.jsp").forward(req, resp);
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ProjectService projectsService = new ProjectService(new ProjectBase());
        String companiesId1 = req.getParameter("CompaniesId");
        String companiesId2 = req.getParameter("companiesId");
        String projectIds = req.getParameter("projectId");
        CompanyDao build = CompanyDao.builder()
                .name_companies(req.getParameter("Name_company"))
                .country_companies(req.getParameter("Country_company"))
                .build();
        if (build != null && companiesId2 == null && projectIds == null) {

            companiesService.create(build);
            resp.sendRedirect("/companies");

        } else if (projectIds == null && build == null) {


            List<ProjectDto> project = projectsService.getProjectsWithNullableCompany();
            req.setAttribute("Projects", project);
            req.setAttribute("Back", companiesId1);
            req.getRequestDispatcher("/jsp/company_new_projects.jsp").forward(req, resp);

        } else if (companiesId2 != null) {
            assert build != null;
            build.setId(Integer.parseInt(companiesId2));
            companiesService.updateCompany(build);
            resp.sendRedirect("/companies");

        } else {

            Integer companiesId = Integer.parseInt(companiesId1);
            Optional<CompanyDao> companiesDao = companiesService.get(companiesId);
            CompanyDao companiesDao1 = companiesDao.get();
            List<Integer> projectId = Arrays.stream(req.getParameterValues("projectId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Optional<ProjectDao>> collect = projectId.stream()
                    .map(p -> projectsService.get(p))
                    .collect(Collectors.toList());

            List<ProjectDao> projectsDaoStream = collect.stream()
                    .map(p -> p.get())
                    .collect(Collectors.toList());

            projectsDaoStream.forEach(c ->
            {
                c.setCompany(companiesDao1);
                projectsService.update(c);
            });
            resp.sendRedirect("/companies?projects=" + companiesId1);

        }
    }
}
