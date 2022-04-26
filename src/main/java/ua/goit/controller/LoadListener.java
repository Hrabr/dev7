package ua.goit.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import ua.goit.config.DbMigration;
import ua.goit.service.*;


@WebListener
public class LoadListener implements ServletContextListener {


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbMigration.migrate();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("developersService",new DevelopersService());
        servletContext.setAttribute("skillsService", new SkillsService());
        servletContext.setAttribute("projectsService", new ProjectsService());
        servletContext.setAttribute("companiesService", new CompaniesService());
        servletContext.setAttribute("customersService", new CustomersService());

    }
}
