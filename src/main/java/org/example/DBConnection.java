package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    static String urlDB, user, password;
    static Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        // chargement du driver jdbc
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.urlDB = "jdbc:mysql://localhost:1911/testpersonne";
        this.user = "root";
        this.password = "";
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connection = DriverManager.getConnection(urlDB, connectionProps);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            new DBConnection();
        }
        return connection;
    }
}
