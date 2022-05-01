package ua.goit.service;

import lombok.SneakyThrows;
import ua.goit.base_command.ProjectsCommand;
import ua.goit.convert.ConverterProjects;
import ua.goit.dao.ProjectsDao;
import ua.goit.dto.ProjectsDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectsService {
    private ConverterProjects converterProjects;
    private ProjectsCommand projectsCommand;
    private DbHelper dbHelper;
    private final String SAVE_PROJECT = "INSERT INTO projects (name_projects,start_project,cost_project) VALUES (?,?,?);";
    private final String SAVE_DEVELOPERS_PROJECT = "INSERT INTO developers_projects (developer_id,projects_id) VALUES(?,?);";
    private final String UPDATE_PROJECT = "UPDATE projects SET name_projects=?,cost_project=?,start_project=? WHERE id_projects=?";
    private final String GET_PROJECT = "SELECT * FROM projects WHERE id_projects=?";
    private final String DELETE_PROJECT = "DELETE FROM projects WHERE id_projects=?;";
    private final String DELETE_DEVELOPER_PROJECT = "DELETE FROM developers_projects WHERE developer_id=? AND projects_id=?;";

    public ProjectsService() {
        converterProjects = new ConverterProjects();
        projectsCommand = new ProjectsCommand();
        dbHelper = new DbHelper();
    }

    public List<ProjectsDto> getall() {
        List<ProjectsDao> all = projectsCommand.getAll();
        return all.stream().map(dao -> converterProjects.from(dao)).collect(Collectors.toList());
    }

    public Integer save(ProjectsDto dto) {
        ProjectsDao dao = converterProjects.to(dto);
        return dbHelper.getWithPreparedStatementWithId(SAVE_PROJECT, ps -> {
            try {
                ps.setString(1, dao.getName_projects());
                ps.setDate(2, Date.valueOf(dao.getStart_project()));
                ps.setObject(3, dao.getCost_project());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int saveDevelopersProject(Integer idDev, Integer idProject) {
        return dbHelper.executeWithPreparedStatement(SAVE_DEVELOPERS_PROJECT, ps -> {
            try {
                ps.setInt(1, idDev);
                ps.setInt(2, idProject);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public Integer update(ProjectsDto dto) {
        ProjectsDao dao = converterProjects.to(dto);
        return dbHelper.getWithPreparedStatementWithId(UPDATE_PROJECT, ps -> {

            try {
                ps.setString(1, dao.getName_projects());
                ps.setObject(2, dao.getCost_project());
                ps.setDate(3, Date.valueOf(dao.getStart_project()));
                ps.setInt(4, dao.getId_projects());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    @SneakyThrows
    public ProjectsDto get(int i) {
        ResultSet withPreparedStatement = dbHelper.getWithPreparedStatement(GET_PROJECT, ps -> {

            try {
                ps.setInt(1, i);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        while (withPreparedStatement.next()) {
            ProjectsDao projectsDao = projectsCommand.mapToEntity(withPreparedStatement);
            return converterProjects.from(projectsDao);
        }
        return null;
    }

    public int deleteProject(String projectId) {
        int id = Integer.parseInt(projectId);
        return dbHelper.executeWithPreparedStatement(DELETE_PROJECT, ps -> {

            try {
                ps.setInt(1, id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int deleteDeveloperProject(String developer, String project) {
        int developerId = Integer.parseInt(developer);
        int projectId = Integer.parseInt(project);
        return dbHelper.executeWithPreparedStatement(DELETE_DEVELOPER_PROJECT, ps -> {

            try {
                ps.setInt(1, developerId);
                ps.setInt(2, projectId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
