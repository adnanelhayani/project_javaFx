package com.example.project_esalaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connectionDB {
    private static Connection con=null;
    private connectionDB() {
    }
    public static Connection openConnection() throws SQLException {
        if(con==null)
           con= (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esalaf","root","");
        return con;
    }
    public static void closeConnection() {
        if(con!=null)
            con=null;
    }
}
