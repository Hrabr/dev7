package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.dao.CompaniesDao;
import ua.goit.dto.CompaniesDto;
import ua.goit.dto.DevelopersDto;
import ua.goit.dto.ProjectsDto;
import ua.goit.service.CompaniesService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/companies/*")
public class CompaniesServlet extends HttpServlet {

    CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesService=(CompaniesService) getServletContext().getAttribute("companiesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<CompaniesDto> all = companiesService.getAll();
       req.setAttribute("Companies",all);
         String projects = req.getParameter("projects");
         String requestURI = req.getRequestURI();
        String remove = req.getParameter("remove");
         String edit = req.getParameter("edit");
        if(requestURI.equals("/companies/remove")) {
              String id = req.getParameter("id");
              String back = req.getParameter("back");
             int integer = Integer.parseInt(id);
              int i = companiesService.saveNullProject(integer);
             req.getRequestDispatcher("/companies?projects="+back).forward(req,resp);
         }else if(requestURI.equals("/companies/New")){
             req.getRequestDispatcher("/jsp/new_companies.jsp").forward(req,resp);
        }else if(requestURI.equals("/companies/new")){
     List<ProjectsDto> project = companiesService.getProject();
     String aNew = req.getParameter("new");
    req.setAttribute("Projects",project);
    req.setAttribute("Back",aNew);
    req.getRequestDispatcher("/jsp/company_new_projects.jsp").forward(req,resp);
}else if(projects==null&&remove==null&&edit==null){
             req.getRequestDispatcher("/jsp/companies.jsp").forward(req,resp);
         }else if(projects!=null&&remove==null){
            int i = Integer.parseInt(projects);
            for(CompaniesDto p : all){
                if(i==p.getId_companies()){

                    req.setAttribute("Projects",p.getProjectsDto());
                    req.setAttribute("Name",p.getName_companies());
                    req.setAttribute("Back",i);
                    req.getRequestDispatcher("/jsp/с_projects.jsp").forward(req,resp);
                }
            }
         }else if(remove!=null){
             int remove1 = companiesService.remove(remove);
            List<CompaniesDto> all1 = companiesService.getAll();
            req.setAttribute("Companies",all1);
            req.getRequestDispatcher("/jsp/companies.jsp").forward(req,resp);
        }else if(edit!=null){
            for(CompaniesDto dto: all){
                int integer = Integer.valueOf(edit);
                if(integer==dto.getId_companies()){
                    req.setAttribute("company",dto);
                    req.getRequestDispatcher("/jsp/edit_companies.jsp").forward(req,resp);
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String companiesId1 = req.getParameter("CompaniesId");
         String companiesId2 = req.getParameter("companiesId");
       String projectIds= req.getParameter("projectId");
        CompaniesDto build = CompaniesDto.builder().name_companies(req.getParameter("Name_company"))
                .country_companies(req.getParameter("Country_company")).build();
      if(build!=null&&companiesId2==null&&projectIds==null){
           int save = companiesService.save(build);
         if(save!=0){
             req.setAttribute("Save","Company save");
             req.getRequestDispatcher("/jsp/new_companies.jsp").forward(req,resp);
         }else{
             req.setAttribute("Save","Company not save");
             req.getRequestDispatcher("/jsp/new_companies.jsp").forward(req,resp);
         }

      }else if(projectIds==null&&build==null){
            List<ProjectsDto> project = companiesService.getProject();
            req.setAttribute("Projects",project);
            req.setAttribute("Back",companiesId1);
            req.getRequestDispatcher("/jsp/company_new_projects.jsp").forward(req,resp);
        }else if(companiesId2!=null){
          assert build != null;
          build.setId_companies(Integer.parseInt(companiesId2));
           int update = companiesService.update(build);
          if (update != 0) {
             CompaniesDto dto = companiesService.get(update);
              req.setAttribute("company", dto);
              req.setAttribute("Save", "Company update");
          } else {
              req.setAttribute("Save", "Company not update");
          }
          req.getRequestDispatcher("/jsp/edit_companies.jsp").forward(req, resp);

      }
      else {
            Integer companiesId = Integer.parseInt(companiesId1);
        Set<Integer> projectId = Arrays.stream(req.getParameterValues("projectId"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
       projectId.stream().forEach(p-> companiesService.saveCompaniesProject(p, companiesId));
        List<ProjectsDto> project = companiesService.getProject();
        req.setAttribute("Projects",project);
        req.setAttribute("Back",companiesId1);
        req.getRequestDispatcher("/jsp/company_new_projects.jsp").forward(req,resp);}
    }
}
