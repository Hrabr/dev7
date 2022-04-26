package ua.goit.config;

import org.flywaydb.core.Flyway;

import java.sql.SQLException;


public class DbMigration {

    public static void migrate() throws SQLException {
        PropertiesUtil p = new PropertiesUtil();
        Flyway flyway = Flyway.configure()
                .dataSource(new PostgresHikariProvider().readProperties(p.getHostname(), p.getPort(), p.getSchema(), p.getUser(), p.getPassword(), p.getJdbcDriver())).load();
        flyway.migrate();

    }
}
