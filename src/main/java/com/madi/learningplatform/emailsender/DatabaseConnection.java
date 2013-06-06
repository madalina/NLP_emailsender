package com.madi.learningplatform.emailsender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
    private Connection databaseConn;
    private static final DatabaseConnection instance = new DatabaseConnection();
    private static final Logger log = LoggerFactory.getLogger(DatabaseConnection.class);
    
    private DatabaseConnection() {}
    
    public static Connection getConnection() {
        if(getInstance().databaseConn == null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String connectionString = "jdbc:mysql://debianhomeserver.local/learningplatform?user=root&password=debianmysql";
                connectionString = "jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com/learningplatform?user=madi&password=madimysql";
                getInstance().databaseConn = DriverManager.getConnection(connectionString);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        return getInstance().databaseConn;
    }
    
    public static DatabaseConnection getInstance() {
        return instance;
    }
}
