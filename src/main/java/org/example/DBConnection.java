package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static String nomDB, urlDB, user, password;
    private static Connection connection;

    private DBConnection(String nomBase) throws SQLException, ClassNotFoundException {
        // chargement du driver jdbc
        Class.forName("com.mysql.cj.jdbc.Driver");
        urlDB = "jdbc:mysql://localhost:1911/" + nomDB;
        user = "root";
        password = "";
        nomDB = nomBase;
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connection = DriverManager.getConnection(urlDB, connectionProps);
    }

    public static Connection getConnection(String nomDB) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            new DBConnection(nomDB);
        }
        return connection;
    }

    public static void setNomDB(String nomDB) throws SQLException, ClassNotFoundException {
        new DBConnection(nomDB);
    }
}
