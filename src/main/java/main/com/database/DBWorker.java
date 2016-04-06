package main.com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by andreykazakov on 22.03.16.
 */
public class DBWorker {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/QRCodes";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Duke3355";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}
