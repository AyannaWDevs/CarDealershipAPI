package com.pluralsight.dealership.dealership_api.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource basicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");
        return dataSource;

    }
    private static final String URL = "jdbc:mysql://localhost:3306/cardealership";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yearup";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}