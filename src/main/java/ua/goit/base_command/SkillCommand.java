package ua.goit.base_command;

import ua.goit.dao.SkillDao;
import ua.goit.service.ServiceCommand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillCommand extends ServiceCommand<SkillDao> {

    @Override
    public String getTableName() {
       return  "skill";
    }




    @Override
    public SkillDao mapToEntity(ResultSet resultSet) throws SQLException {
       return SkillDao.builder().id_skill(resultSet.getInt("id_skill"))
                .level_skill(resultSet.getString("level_skill")).language(resultSet.getString("language"))
                .build();
    }
}
