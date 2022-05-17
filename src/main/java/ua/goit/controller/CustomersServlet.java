package ua.goit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.goit.base_service.ProjectBase;
import ua.goit.dao.CustomerDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dto.CustomerDto;
import ua.goit.dto.ProjectDto;
import ua.goit.service.CustomerService;
import ua.goit.service.ProjectService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/customers/*")
public class CustomersServlet extends HttpServlet {

    private CustomerService customersService;

    @Override
    public void init() throws ServletException {
        this.customersService = (CustomerService) getServletContext().getAttribute("customersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final ProjectService projectsService = new ProjectService(new ProjectBase());
        List<CustomerDto> all = customersService.getAll();
        req.setAttribute("Customers", all);
        String projects = req.getParameter("projects");
        String requestURI = req.getRequestURI();
        String remove = req.getParameter("remove");
        String edit = req.getParameter("edit");

        if (requestURI.equals("/customers/remove")) {
            String id = req.getParameter("id");
            String back = req.getParameter("back");
            int idProject = Integer.parseInt(id);
            customersService.updateNullProject(idProject);
            req.getRequestDispatcher("/customers?projects=" + back).forward(req, resp);
        } else if (requestURI.equals("/customers/New")) {
            req.getRequestDispatcher("/jsp/new_customers.jsp").forward(req, resp);
        } else if (requestURI.equals("/customers/Back")) {
            final List<ProjectDto> projectsWithNullableCustomer = projectsService.getProjectsWithNullableCustomer();

            String aNew = req.getParameter("new");
            req.setAttribute("Projects", projectsWithNullableCustomer);
            req.setAttribute("Back", aNew);
            req.getRequestDispatcher("/jsp/customer_new_projects.jsp").forward(req, resp);
        } else if (projects == null && remove == null && edit == null) {
            req.getRequestDispatcher("/jsp/customers.jsp").forward(req, resp);
        } else if (projects != null && remove == null) {
            int projectBack = Integer.parseInt(projects);
            for (CustomerDto cuD : all) {
                if (projectBack == cuD.getId_customers()) {
                    req.setAttribute("Projects", cuD.getProjectsDto());
                    req.setAttribute("Name", cuD.getName_customers());
                    req.setAttribute("Back", projectBack);
                    req.getRequestDispatcher("/jsp/сu_projects.jsp").forward(req, resp);
                }
            }
        } else if (remove != null) {
            int removeCustomer = Integer.parseInt(remove);
            Optional<CustomerDao> customerDao = customersService.get(removeCustomer);
            CustomerDao customerDao1 = customerDao.get();
            customersService.delete(customerDao1);
            resp.sendRedirect("/customers");

        } else if (edit != null) {
            for (CustomerDto dto : all) {
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

        final ProjectService projectsService = new ProjectService(new ProjectBase());
        String customerId1 = req.getParameter("CustomerId");
        String customerId2 = req.getParameter("customerId");
        String projectIds = req.getParameter("projectId");
        CustomerDao build = CustomerDao.builder()
                .name_customers(req.getParameter("Name_customer"))
                .country_customers(req.getParameter("Country_customer"))
                .build();
        if (build != null && customerId2 == null && projectIds == null) {

            customersService.create(build);
            resp.sendRedirect("/customers");

        } else if (projectIds == null && build == null) {

            List<ProjectDto> project = projectsService.getProjectsWithNullableCustomer();
            req.setAttribute("Projects", project);
            req.setAttribute("Back", customerId1);
            req.getRequestDispatcher("/jsp/customer_new_projects.jsp").forward(req, resp);

        } else if (customerId2 != null) {
            assert build != null;
            build.setId(Integer.parseInt(customerId2));
            customersService.updateCustomer(build);
            resp.sendRedirect("/customers");

        } else {
            Integer customerId = Integer.parseInt(customerId1);
            Optional<CustomerDao> customerDao = customersService.get(customerId);
            CustomerDao customerDao1 = customerDao.get();

            List<Integer> projectId = Arrays.stream(req.getParameterValues("projectId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Optional<ProjectDao>> collect = projectId.stream()
                    .map(p -> projectsService.get(p))
                    .collect(Collectors.toList());

            List<ProjectDao> projectsDaoStream = collect.stream()
                    .map(p -> p.get())
                    .collect(Collectors.toList());

            projectsDaoStream.forEach(c -> {
                c.setCustomer(customerDao1);
                projectsService.update(c);
            });
            resp.sendRedirect("/customers?projects=" + customerId1);

        }
    }

}
