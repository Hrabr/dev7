package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.dto.CustomersDto;
import ua.goit.dto.ProjectsDto;
import ua.goit.service.CustomersService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/customers/*")
public class CustomersServlet extends HttpServlet {

    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        this.customersService = (CustomersService) getServletContext().getAttribute("customersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomersDto> all = customersService.getAll();
        req.setAttribute("Customers", all);
        String projects = req.getParameter("projects");
        String requestURI = req.getRequestURI();
        String remove = req.getParameter("remove");
        String edit = req.getParameter("edit");
        if (requestURI.equals("/customers/remove")) {
            String id = req.getParameter("id");
            String back = req.getParameter("back");
            int integer = Integer.parseInt(id);
            int i = customersService.saveNullProject(integer);
            req.getRequestDispatcher("/customers?projects=" + back).forward(req, resp);
        } else if (requestURI.equals("/customers/New")) {
            req.getRequestDispatcher("/jsp/new_customers.jsp").forward(req, resp);
        } else if (requestURI.equals("/customers/new")) {
            List<ProjectsDto> project = customersService.getProject();
            String aNew = req.getParameter("new");
            req.setAttribute("Projects", project);
            req.setAttribute("Back", aNew);
            req.getRequestDispatcher("/jsp/customer_new_projects.jsp").forward(req, resp);
        } else if (projects == null && remove == null && edit == null) {
            req.getRequestDispatcher("/jsp/customers.jsp").forward(req, resp);
        } else if (projects != null && remove == null) {
            int i = Integer.parseInt(projects);
            for (CustomersDto cuD : all) {
                if (i == cuD.getId_customers()) {
                    req.setAttribute("Projects", cuD.getProjectsDto());
                    req.setAttribute("Name", cuD.getName_customers());
                    req.setAttribute("Back", i);
                    req.getRequestDispatcher("/jsp/с_projects.jsp").forward(req, resp);
                }
            }
        } else if (remove != null) {
            int remove1 = customersService.remove(remove);
            List<CustomersDto> all1 = customersService.getAll();
            req.setAttribute("Customers", all1);
            req.getRequestDispatcher("/jsp/customers.jsp").forward(req, resp);
        } else if (edit != null) {
            for (CustomersDto dto : all) {
                int integer = Integer.valueOf(edit);
                if (integer == dto.getId_customers()) {
                    req.setAttribute("customer", dto);
                    req.getRequestDispatcher("/jsp/edit_customers.jsp").forward(req, resp);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId1 = req.getParameter("CustomerId");
        String customerId2 = req.getParameter("customerId");
        String projectIds= req.getParameter("projectId");
        CustomersDto build = CustomersDto.builder().name_customers(req.getParameter("Name_customer"))
                .country_customers(req.getParameter("Country_customer")).build();
        if(build!=null&&customerId2==null&&projectIds==null){
            int save = customersService.save(build);
            if(save!=0){
                req.setAttribute("Save","Customer save");
                req.getRequestDispatcher("/jsp/new_customers.jsp").forward(req,resp);
            }else{
                req.setAttribute("Save","Customer not save");
                req.getRequestDispatcher("/jsp/new_customers.jsp").forward(req,resp);
            }

        }else if(projectIds==null&&build==null){
            List<ProjectsDto> project = customersService.getProject();
            req.setAttribute("Projects",project);
            req.setAttribute("Back",customerId1);
            req.getRequestDispatcher("/jsp/customer_new_projects.jsp").forward(req,resp);
        }else if(customerId2!=null){
            assert build != null;
            build.setId_customers(Integer.parseInt(customerId2));
            int update = customersService.update(build);
            if (update != 0) {
                CustomersDto dto = customersService.get(update);
                req.setAttribute("customer", dto);
                req.setAttribute("Save", "Customer update");
            } else {
                req.setAttribute("Save", "Customer not update");
            }
            req.getRequestDispatcher("/jsp/edit_customers.jsp").forward(req, resp);

        }
        else {
            Integer customerId = Integer.parseInt(customerId1);
            Set<Integer> projectId = Arrays.stream(req.getParameterValues("projectId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            projectId.stream().forEach(p-> customersService.saveCustomersProject(p, customerId));
            List<ProjectsDto> project = customersService.getProject();
            req.setAttribute("Projects",project);
            req.setAttribute("Back",customerId1);
            req.getRequestDispatcher("/jsp/customer_new_projects.jsp").forward(req,resp);}
    }

}
