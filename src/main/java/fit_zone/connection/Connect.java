package fit_zone.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection getConnection(){
    Connection connection = null;
    String bdd = "fit_zone";
    String url = "jdbc:mysql://localhost:3306/" + bdd;
    String user = "root";
    String password = "santimysql";
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);;
    }catch (Exception e){
        System.out.println("Error in db connection" + e.getMessage());
    }
    return connection;
    }
}
