package lk.ijse.preschool.DAO.custom;

import lk.ijse.preschool.DAO.CrudDAO;
import lk.ijse.preschool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student> {
    public ArrayList<String> getIds() throws SQLException;
}
