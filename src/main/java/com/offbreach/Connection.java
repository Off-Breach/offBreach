package com.offbreach;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {
      private BasicDataSource dataSource;

    public Connection() {
        this.dataSource = new BasicDataSource();
        
         dataSource​.setDriverClassName("com.mysql.jdbc.Driver");
         dataSource​.setUrl("jdbc:mysql://localhost:3306/offbreach");
         dataSource​.setUsername("root");
         dataSource​.setPassword("01M@theus");

    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
