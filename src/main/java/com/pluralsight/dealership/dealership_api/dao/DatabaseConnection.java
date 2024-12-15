//package com.pluralsight.dealership.dealership_api.dao;
////is this class needed still?
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//@Configuration
//public class DatabaseConnection {
//    private BasicDataSource basicDataSource;
//private static String URL = "jdbc:mysql://localhost:3306/cardealership";
//private static String USERNAME = "root";
//private static String PASSWORD = "yearup";
//
//    public static Connection connect() throws SQLException {
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }
//
//
//
//    @Autowired
//    public DatabaseConnection(@Value("${spring.datasource.url}") String URL,
//                              @Value("${spring.datasource.username}") String USERNAME,
//                              @Value("${spring.datasource.password}") String PASSWORD) {
//        basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(URL);
//        basicDataSource.setUsername(USERNAME);
//        basicDataSource.setPassword(PASSWORD);
//    }
//
//    public static Connection connect() {
//        return null;
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        return basicDataSource;
//    }
//}
