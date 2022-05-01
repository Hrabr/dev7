package ua.goit.service;

import lombok.SneakyThrows;
import ua.goit.base_command.CompaniesCommand;
import ua.goit.base_command.ProjectsCommand;
import ua.goit.convert.ConverterCompanies;
import ua.goit.convert.ConverterProjects;
import ua.goit.dao.CompaniesDao;
import ua.goit.dao.ProjectsDao;
import ua.goit.dto.CompaniesDto;
import ua.goit.dto.ProjectsDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CompaniesService {
    private ConverterCompanies converterCompanies;
    private CompaniesCommand companiesCommand;
    private ProjectsCommand projectsCommand;
    private ConverterProjects converterProjects;
    private DbHelper dbHelper;
    private final String SAVE_COMPANY_ID_TO_PROJECT = "UPDATE projects SET companies_id=? WHERE id_projects=?;";
    private final String SAVE_NULL_TO_PROJECT = "UPDATE projects SET companies_id=null WHERE id_projects=?;";
    private final String SAVE = "INSERT INTO companies (name_companies,country_companies)VALUES(?,?);";
    private final String DELETE_COMPANY = "DELETE FROM companies WHERE id_companies=?;";
    private final String UPDATE_DEVELOPERS = "UPDATE companies SET name_companies=?,country_companies=? WHERE id_companies=?";
    private final String GET_COMPANIES = "SELECT * FROM companies WHERE id_companies=?";

    public CompaniesService() {
        companiesCommand = new CompaniesCommand();
        converterCompanies = new ConverterCompanies();
        projectsCommand = new ProjectsCommand();
        converterProjects = new ConverterProjects();
        dbHelper = new DbHelper();
    }

    public List<CompaniesDto> getAll() {
        List<CompaniesDao> all = companiesCommand.getAll();
        for (CompaniesDao companiesDao : all) {
            List<ProjectsDao> projectCompanies = projectsCommand.getProjectCompanies(companiesDao.getId_companies());
            companiesDao.setProjectsDao(projectCompanies);
        }
        return all.stream().map(dao -> converterCompanies.from(dao)).collect(Collectors.toList());
    }

    public List<ProjectsDto> getProject() {
        List<ProjectsDao> projectCompanies = projectsCommand.getProjectNull("companies_id");
        return projectCompanies.stream().map(dao -> converterProjects.from(dao)).collect(Collectors.toList());
    }

    public int saveCompaniesProject(int project, int company) {
        return dbHelper.executeWithPreparedStatement(SAVE_COMPANY_ID_TO_PROJECT, ps -> {
            try {
                ps.setInt(1, company);
                ps.setInt(2, project);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int saveNullProject(int project) {
        return dbHelper.executeWithPreparedStatement(SAVE_NULL_TO_PROJECT, ps -> {
            try {
                ps.setInt(1, project);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int save(CompaniesDto dto) {
        CompaniesDao dao = converterCompanies.to(dto);
        return dbHelper.getWithPreparedStatementWithId(SAVE, ps -> {
            try {
                ps.setString(1, dao.getName_companies());
                ps.setString(2, dao.getCountry_companies());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int remove(String id) {
        int number = Integer.parseInt(id);

        return dbHelper.executeWithPreparedStatement(DELETE_COMPANY, ps -> {

            try {
                ps.setInt(1, number);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public Integer update(CompaniesDto dto) {
        CompaniesDao dao = converterCompanies.to(dto);
        return dbHelper.getWithPreparedStatementWithId(UPDATE_DEVELOPERS, ps -> {

            try {
                ps.setString(1, dao.getName_companies());
                ps.setString(2, dao.getCountry_companies());
                ps.setInt(3, dao.getId_companies());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    @SneakyThrows
    public CompaniesDto get(int i) {
        ResultSet withPreparedStatement = dbHelper.getWithPreparedStatement(GET_COMPANIES, ps -> {

            try {
                ps.setInt(1, i);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        while (withPreparedStatement.next()) {
            CompaniesDao dao = companiesCommand.mapToEntity(withPreparedStatement);
            return converterCompanies.fromWithoutProjects(dao);
        }
        return null;
    }
}
