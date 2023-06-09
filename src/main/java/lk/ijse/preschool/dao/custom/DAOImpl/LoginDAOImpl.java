package lk.ijse.preschool.dao.custom.DAOImpl;

import lk.ijse.preschool.dao.SQLUtil;
import lk.ijse.preschool.dao.custom.LoginDAO;
import lk.ijse.preschool.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE user_name= ? AND password=?";
        ResultSet resultSet = SQLUtil.execute(sql, username, password);
        if(resultSet.next()){
            return true;
        }
        return false;
    }
}
