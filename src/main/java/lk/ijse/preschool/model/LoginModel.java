package lk.ijse.preschool.model;

import lk.ijse.preschool.DAO.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE user_name= ? AND password=?";
        ResultSet resultSet = SQLUtil.execute(sql, username, password);
        if(resultSet.next()){
            return true;
        }
        return false;
    }
}
