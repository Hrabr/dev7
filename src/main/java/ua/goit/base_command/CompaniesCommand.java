package ua.goit.base_command;

import ua.goit.dao.CompaniesDao;
import ua.goit.service.ServiceCommand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompaniesCommand extends ServiceCommand<CompaniesDao> {

    @Override
    public String getTableName() {
        return "companies";
    }




    @Override
    public CompaniesDao mapToEntity(ResultSet resultSet) throws SQLException {
        return CompaniesDao.builder().id_companies(resultSet.getInt("id_companies"))
                .name_companies(resultSet.getString("name_companies"))
                .country_companies(resultSet.getString("country_companies")).build();
    }
}
