package main.com.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by andreykazakov on 22.03.16.
 */
public class dbMain {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbPerson";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Duke3355";




    public static void main(String[] args){

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            if (!connection.isClosed()){
//                System.out.println("Соедиение с БД установлено успешно!");
//            }
//
//            connection.close();
//
//            if (connection.isClosed()){
//                System.out.println("Соедиение с БД закрыто!");
//            }

        } catch (SQLException e) {
            System.err.println("Неудалось загрузить калсс драйвера!");
        }

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
//            System.out.println(connection.isClosed());
//            statement.execute("INSERT INTO animal(anim_name, anim_desc) VALUES ('name','desc');");   //INSERT
//            int res = statement.executeUpdate("update animal set anim_name = 'Araz' where id =1;");   // UPDATE
//            System.out.println(res);
//            ResultSet res = statement.executeQuery("select * from animal");     // SELECT

//            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES ('name2','desc2');");
//            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES ('name3','desc3');");
//            statement.addBatch("INSERT INTO animal(anim_name, anim_desc) VALUES ('name4','desc4');");
//
//            statement.executeBatch();
//
//            statement.clearBatch();

            boolean status = statement.isClosed();
            System.out.println(status);

            System.out.println(statement.getConnection().toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
