package lk.ijse.preschool.dao.custom;

import lk.ijse.preschool.dao.CrudDAO;
import lk.ijse.preschool.entity.Event;
import lk.ijse.preschool.entity.User;

import java.sql.SQLException;

public interface LoginDAO  extends CrudDAO<User> {
    public  boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException;
}
