package com.offbreach;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Connection {

    private JdbcTemplate connection;

    public Connection() {
        BasicDataSource dataSource = new BasicDataSource();
        
         dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
         dataSource.setUrl("jdbc:mysql://localhost:3306/offbreach");
         dataSource.setUsername("root");
         dataSource.setPassword("sptech");

         connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getDataSource() {
        return connection;
    }
}
