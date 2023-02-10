/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQLServer {

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String Url = "jdbc:sqlserver://MAOU:1433;databaseName=EndGame"; 
            String User = "sa";
            String Password = "123456";
            connection = DriverManager.getConnection(Url, User, Password);
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("can't connect");
        }
        return connection;
    }
}
