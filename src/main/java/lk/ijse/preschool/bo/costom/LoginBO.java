package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public  boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException;
}
