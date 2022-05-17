package ua.goit.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import ua.goit.base_service.*;
import ua.goit.config.DbMigration;
import ua.goit.service.*;


@WebListener
public class LoadListener implements ServletContextListener {

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbMigration.migrate();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("developersService", new DeveloperService(new DeveloperBase()));
        servletContext.setAttribute("skillsService", new SkillsService(new SkillBase()));
        servletContext.setAttribute("projectsService", new ProjectService(new ProjectBase()));
        servletContext.setAttribute("companiesService", new CompaniesService (new CompanyBase()));
        servletContext.setAttribute("customersService", new CustomerService(new CustomerBase()));

    }
}
