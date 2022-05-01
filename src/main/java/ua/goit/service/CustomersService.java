package ua.goit.service;

import lombok.SneakyThrows;
import ua.goit.base_command.CustomersCommand;
import ua.goit.base_command.ProjectsCommand;
import ua.goit.convert.ConverterCustomer;
import ua.goit.convert.ConverterProjects;
import ua.goit.dao.CustomersDao;
import ua.goit.dao.ProjectsDao;
import ua.goit.dto.CustomersDto;
import ua.goit.dto.ProjectsDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersService {
    private ConverterCustomer converterCustomer;
    private CustomersCommand customersCommand;
    private ProjectsCommand projectsCommand;
    private ConverterProjects converterProjects;
    private DbHelper dbHelper;
    private final String SAVE_CUSTOMER_ID_TO_PROJECT = "UPDATE projects SET customers_id=? WHERE id_projects=?;";
    private final String SAVE_NULL_TO_PROJECT = "UPDATE projects SET customers_id=null WHERE id_projects=?;";
    private final String SAVE = "INSERT INTO customers (name_customers,country_customers)VALUES(?,?);";
    private final String DELETE_CUSTOMER = "DELETE FROM customers WHERE id_customers=?;";
    private final String UPDATE_DEVELOPERS = "UPDATE customers SET name_customers=?,country_customers=? WHERE id_customers=?";
    private final String GET_CUSTOMERS = "SELECT * FROM customers WHERE id_customers=?";

    public CustomersService() {
        this.converterCustomer = new ConverterCustomer();
        this.customersCommand = new CustomersCommand();
        this.projectsCommand = new ProjectsCommand();
        this.converterProjects = new ConverterProjects();
        this.dbHelper = new DbHelper();
    }

    public List<CustomersDto> getAll() {
        List<CustomersDao> all = customersCommand.getAll();
        for (CustomersDao customersDao : all) {
            List<ProjectsDao> projectCustomers = projectsCommand.getProjectCustomers(customersDao.getId_customers());
            customersDao.setProjectsDao(projectCustomers);
        }
        return all.stream().map(dao -> converterCustomer.from(dao)).collect(Collectors.toList());
    }

    public List<ProjectsDto> getProject() {
        List<ProjectsDao> projectCustomers = projectsCommand.getProjectNull("customers_id");
        return projectCustomers.stream().map(dao -> converterProjects.from(dao)).collect(Collectors.toList());
    }

    public int saveCustomersProject(int project, int customer) {
        return dbHelper.executeWithPreparedStatement(SAVE_CUSTOMER_ID_TO_PROJECT, ps -> {
            try {
                ps.setInt(1, customer);
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

    public int save(CustomersDto dto) {
        CustomersDao dao = converterCustomer.to(dto);
        return dbHelper.getWithPreparedStatementWithId(SAVE, ps -> {
            try {
                ps.setString(1, dao.getName_customers());
                ps.setString(2, dao.getCountry_customers());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public int remove(String id) {
        int number = Integer.parseInt(id);

        return dbHelper.executeWithPreparedStatement(DELETE_CUSTOMER, ps -> {

            try {
                ps.setInt(1, number);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public Integer update(CustomersDto dto) {
        CustomersDao dao = converterCustomer.to(dto);
        return dbHelper.getWithPreparedStatementWithId(UPDATE_DEVELOPERS, ps -> {

            try {
                ps.setString(1, dao.getName_customers());
                ps.setString(2, dao.getCountry_customers());
                ps.setInt(3, dao.getId_customers());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    @SneakyThrows
    public CustomersDto get(int i) {
        ResultSet withPreparedStatement = dbHelper.getWithPreparedStatement(GET_CUSTOMERS, ps -> {

            try {
                ps.setInt(1, i);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        while (withPreparedStatement.next()) {
            CustomersDao dao = customersCommand.mapToEntity(withPreparedStatement);
            return converterCustomer.fromWithoutProjects(dao);
        }
        return null;
    }
}
