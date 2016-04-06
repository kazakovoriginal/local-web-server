package main.com.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by andreykazakov on 22.03.16.
 */
public class Main {

    public static final String INSERT_NEW = "insert into dish VALUES(?,?,?,?,?,?,?) ";
    public static final String INSERT_to_QR = "insert into testQR(link) values(?)";

    public static void main(String[] args){
        DBWorker worker = new DBWorker();

        addLinkToQRCodesDb(worker, "https://vk.com/kazakov_original");

//        testQRwithDB res = selecteIdFromQRCodesDb(worker, "link1");
//
//        System.out.println("id is: " + res.getId());


    }

    public static void addLinkToQRCodesDb(DBWorker worker, String str)
    {

        try {

            Statement statement = worker.getConnection().createStatement();

            PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_to_QR);
            preparedStatement.setString(1,str);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static testQRwithDB selecteIdFromQRCodesDb(DBWorker worker, String link)
    {

        String query = "select * from testQR where link = '" + link + "'";
        testQRwithDB tqwd = new testQRwithDB();

        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){


                tqwd.setId(resultSet.getInt("id"));
                tqwd.setLink(resultSet.getString("link"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tqwd;
    }

}

//        _______lesson 2______
//        public static final String INSERT_NEW = "insert into dish VALUES(?,?,?,?,?,?,?) ";

//        try {
//            Statement statement = worker.getConnection().createStatement();
//
//            PreparedStatement preparedStatement = worker.getConnection().prepareStatement(INSERT_NEW);
//            preparedStatement.setInt(1,1);
//            preparedStatement.setString(2,"tit");
//            preparedStatement.setString(3,"descr");
//            preparedStatement.setFloat(4, 0.2f);
//            preparedStatement.setBoolean(5, true);
//            preparedStatement.setDate(6,new Date(Calendar.getInstance().getTimeInMillis()));
//            preparedStatement.setBlob(7,new FileInputStream("simple.png"));
//
//            preparedStatement.execute();
//
//        } catch (SQLException | FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        _______lesson 1______
//        String query = "select * from user where id = 2";
//
//        try {
//            Statement statement = worker.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()){
//
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setUsername(resultSet.getString("name"));
//                user.setPassword(resultSet.getString("password"));
//
//                System.out.println(user.toString());
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
