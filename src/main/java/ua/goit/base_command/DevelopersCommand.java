package ua.goit.base_command;

import ua.goit.dao.DevelopersDao;
import ua.goit.service.ServiceCommand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersCommand extends ServiceCommand<DevelopersDao> {

    @Override
    public String getTableName() {
       return  "developers";
    }



    @Override
   public   DevelopersDao mapToEntity(ResultSet resultSet) throws SQLException {
        return DevelopersDao.builder().id_developers(resultSet.getInt("id_developers"))
                .salary(resultSet.getBigDecimal("salary")).gender(resultSet.getString("gender"))
                .age(resultSet.getInt("age")).name(resultSet.getString("name")).build();

        }

    }

