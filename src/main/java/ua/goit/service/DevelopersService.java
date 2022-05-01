package ua.goit.service;

import lombok.SneakyThrows;
import ua.goit.base_command.DevelopersCommand;
import ua.goit.base_command.ProjectsCommand;
import ua.goit.base_command.SkillCommand;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.convert.ConverterDevelopers;
import ua.goit.dao.DevelopersDao;
import ua.goit.dao.ProjectsDao;
import ua.goit.dao.SkillDao;
import ua.goit.dto.DevelopersDto;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class DevelopersService {
    private DevelopersCommand developersCommand;
    private ConverterDevelopers converterDevelopers;
    private SkillCommand skillCommand;
    private ProjectsCommand projectsCommand;
    private DbHelper dbHelper;
    PostgresHikariProvider postgresHikariProvider;
    private final String SAVE_DEVELOPER = "INSERT INTO developers (name,age,gender,salary) VALUES (?,?,?,?);";
    private final String UPDATE_DEVELOPERS = "UPDATE developers SET name=?,age=?,gender=?,salary=? WHERE id_developers=?;";
    private final String GET_DEVELOPER = "SELECT * FROM developers WHERE id_developers=?";
    private final String DELETE_DEVELOPER = "DELETE FROM developers WHERE id_developers=?;";

    public DevelopersService() {
        developersCommand = new DevelopersCommand();
        converterDevelopers = new ConverterDevelopers();
        skillCommand = new SkillCommand();
        projectsCommand = new ProjectsCommand();
        postgresHikariProvider = new PostgresHikariProvider();
        dbHelper = new DbHelper();
    }

    public List<DevelopersDto> getAll() {
        List<DevelopersDao> all = developersCommand.getAll();
        for (DevelopersDao d : all) {
            List<SkillDao> byIdSkill = skillCommand.getByIdDevelopersSkill(d.getId_developers());
            d.setSkillDao(byIdSkill);
            List<ProjectsDao> byIdProject = projectsCommand.getByIdDevelopersProjects(d.getId_developers());
            d.setProjectsDao(byIdProject);
        }

        return all.stream().map(dao -> converterDevelopers.from(dao)).collect(Collectors.toList());
    }

    public Integer save(DevelopersDao dao) {
        return dbHelper.getWithPreparedStatementWithId(SAVE_DEVELOPER, ps -> {

            try {
                ps.setString(1, dao.getName());
                ps.setInt(2, dao.getAge());
                ps.setString(3, dao.getGender());
                ps.setObject(4, dao.getSalary());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

    }

    public Integer update(DevelopersDao dao) {
        return dbHelper.getWithPreparedStatementWithId(UPDATE_DEVELOPERS, ps -> {

            try {
                ps.setString(1, dao.getName());
                ps.setInt(2, dao.getAge());
                ps.setString(3, dao.getGender());
                ps.setObject(4, dao.getSalary());
                ps.setInt(5, dao.getId_developers());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    @SneakyThrows
    public DevelopersDto get(int i) {
        ResultSet withPreparedStatement = dbHelper.getWithPreparedStatement(GET_DEVELOPER, ps -> {

            try {
                ps.setInt(1, i);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        while (withPreparedStatement.next()) {
            DevelopersDao dao = developersCommand.mapToEntity(withPreparedStatement);
            return converterDevelopers.fromWithoutProjects(dao);
        }
        return null;
    }

    public int deleteDeveloper(String developer) {
        int developerId = Integer.parseInt(developer);
        return dbHelper.executeWithPreparedStatement(DELETE_DEVELOPER, ps -> {

            try {
                ps.setInt(1, developerId);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
