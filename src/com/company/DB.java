package com.company;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB extends Component {
    // set the access variables here
    private static final String DATABASE = Env.dbName;
    private static final String USER = Env.dbUsername;
    private static final String PASSWORD = Env.dbPassword;
    private static final String CONN = "jdbc:mysql://" + Env.dbHost + ":3306/" + DATABASE;

    // set the database connection here
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN, USER, PASSWORD);
    }

}
