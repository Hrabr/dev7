package ua.goit.base_command;

import ua.goit.dao.CustomersDao;
import ua.goit.service.ServiceCommand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersCommand extends ServiceCommand<CustomersDao> {
    @Override
    public String getTableName() {
        return "customers";
    }




    @Override
    public CustomersDao mapToEntity(ResultSet resultSet) throws SQLException {
     return CustomersDao.builder().id_customers(resultSet.getInt("id_customers"))
              .name_customers(resultSet.getString("name_customers"))
              .country_customers(resultSet.getString("country_customers")).build();
    }
}
