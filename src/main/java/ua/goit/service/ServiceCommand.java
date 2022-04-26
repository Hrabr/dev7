package ua.goit.service;

import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public abstract class ServiceCommand<T> {
    private DbHelper dbHelper;

    public ServiceCommand() {
        dbHelper = new DbHelper();
    }

    public abstract String getTableName();

    public abstract T mapToEntity(ResultSet resultSet) throws SQLException;

    public List<T> getAll() {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s", getTableName());
        try {
            ResultSet resultSet = dbHelper.getWithPreparedStatement(
                    query, ps -> {
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Get all method exception", e);
        }
        return resultList;
    }

    public List<T> getByIdDevelopersSkill(int dao) {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s s inner join developers_skill d_s on s.id_skill=d_s.skill_id where d_s.developer_id=? ", getTableName());
        try {
            ResultSet resultSet = dbHelper.getWithPreparedStatement(
                    query, ps -> {
                        try {
                            ps.setInt(1, dao);
                        } catch (SQLException e) {
                            log.error("Exception function interface ", e);
                        }
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Get by id method exception", e);
        }
        return resultList;
    }

    public List<T> getByIdDevelopersProjects(int dao) {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s p inner join developers_projects d_p on p.id_projects=d_p.projects_id where d_p.developer_id=? ", getTableName());
        try {
            ResultSet resultSet = dbHelper.getWithPreparedStatement(
                    query, ps -> {
                        try {
                            ps.setInt(1, dao);
                        } catch (SQLException e) {
                            log.error("Exception function interface ", e);
                        }
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Get by id method exception", e);
        }
        return resultList;
    }

    public List<T> getProjectCompanies(int dao) {
        List<T> resultList = new ArrayList<>();

        String query = String.format("select * from %s where companies_id=? ", getTableName());
        try {
            ResultSet resultSet = dbHelper.getWithPreparedStatement(
                    query, ps -> {
                        try {
                            ps.setInt(1, dao);
                        } catch (SQLException e) {
                            log.error("Exception function interface ", e);
                        }
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Get by id method exception", e);
        }
        return resultList;
    }

    public List<T> getProjectCustomers(int dao) {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s where customers_id=? ", getTableName());
        try {
            ResultSet resultSet = dbHelper.getWithPreparedStatement(
                    query, ps -> {
                        try {
                            ps.setInt(1, dao);
                        } catch (SQLException e) {
                            log.error("Exception function interface ", e);
                        }
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Get by id method exception", e);
        }
        return resultList;
    }

    public List<T> getProjectNull(String string) {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s where %s is Null ", getTableName(),string);
        try {
            ResultSet resultSet = dbHelper.getWithPreparedStatement(
                    query, ps -> {
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Get by id method exception", e);
        }
        return resultList;
    }
}
