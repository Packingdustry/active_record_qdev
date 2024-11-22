package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private String userName = "root";
    private String password = "";
    private String serverName = "localhost";
    private String portNumber = "1911";
    private String tableName = "personne";
    private static String dbName = "testPersonne";
    private static Connection instance;

    private DBConnection() throws SQLException, ClassNotFoundException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;
        instance = DriverManager.getConnection(urlDB, connectionProps);
    }

    public static Connection getConnection(String nomDB) throws SQLException, ClassNotFoundException {
        if (instance == null) {
            try {
                new DBConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static void setNomDB(String nomDB) throws SQLException, ClassNotFoundException {
        if (nomDB != null && nomDB != dbName) {
            dbName = nomDB;
            instance = null;
        }
    }
}
