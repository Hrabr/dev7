package ua.goit.service;

import lombok.extern.log4j.Log4j2;
import ua.goit.config.PostgresHikariProvider;

import java.sql.*;
import java.util.function.Consumer;

@Log4j2
public class DbHelper {
    private PostgresHikariProvider postgresHikariProvider;

    public DbHelper() {
        this.postgresHikariProvider = new PostgresHikariProvider();
    }


    public int executeWithPreparedStatement(String sql, Consumer<PreparedStatement> psCall) {
        try (Connection connection = postgresHikariProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.accept(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception while trying do SQL request", e);
            return 0;
        }
    }

    public ResultSet getWithPreparedStatement(String sql, Consumer<PreparedStatement> psCall) throws SQLException {
        try (Connection connection = postgresHikariProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            psCall.accept(ps);
            return ps.executeQuery();
        }
    }
    public Integer getWithPreparedStatementWithId(String sql, Consumer<PreparedStatement> psCall)  {
        try (Connection connection = postgresHikariProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            psCall.accept(ps);
             ps.execute();
             ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
               return generatedKeys.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
