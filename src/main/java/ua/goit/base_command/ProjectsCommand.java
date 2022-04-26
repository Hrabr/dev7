package ua.goit.base_command;

import ua.goit.dao.ProjectsDao;
import ua.goit.service.ServiceCommand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectsCommand extends ServiceCommand<ProjectsDao> {

    @Override
    public String getTableName() {
        return "projects";
    }




    @Override
    public ProjectsDao mapToEntity(ResultSet resultSet) throws SQLException {
        return ProjectsDao.builder().id_projects(resultSet.getInt("id_projects"))
                .name_projects(resultSet.getString("name_projects"))
                .cost_project(resultSet.getBigDecimal("cost_project"))
                .start_project(resultSet.getString("start_project")).customers_id(resultSet.getInt("customers_id"))
                .companies_id(resultSet.getInt("companies_id")).build();
    }
}
