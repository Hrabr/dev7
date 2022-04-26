package ua.goit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;


@Log4j2
public class PostgresHikariProvider  {

private HikariDataSource dataSource;
private PropertiesUtil propertiesUtil;

public PostgresHikariProvider(){
    propertiesUtil=new PropertiesUtil();
   dataSource=readProperties(propertiesUtil.getHostname(), propertiesUtil.getPort(), propertiesUtil.getSchema(), propertiesUtil.getUser(), propertiesUtil.getPassword(), propertiesUtil.getJdbcDriver());
}


public HikariDataSource readProperties(String hostname, int port, String databaseName, String username, String password, String jdbcDriver){
    HikariConfig config = new HikariConfig();
    try {
        Class.forName(jdbcDriver);
    } catch (ClassNotFoundException ex) {
        log.error("Not found driver in DB",ex);
    }

    config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", hostname, port, databaseName));
    config.setUsername(username);
    config.setPassword(password);
    config.setMaximumPoolSize(10);
    config.setIdleTimeout(100);
   return  new HikariDataSource(config);
}
    public  Connection getConnection()  {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
           log.error("Not connection to DB",e);
        }
return null;
    }
}
