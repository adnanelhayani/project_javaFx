package com.example.project_esalaf;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class AdminDAO {
    Statement stm;
    public boolean isLogin(admin adm) throws SQLException {
     stm=connectionDB.openConnection().createStatement();
      ResultSet res= stm.executeQuery(" select * from admin where username='"+adm.getUsername()+"' and password='"+adm.getPassword()+"'");
      if (res.next()){
          return true;
      }
      return false;
    }


}
