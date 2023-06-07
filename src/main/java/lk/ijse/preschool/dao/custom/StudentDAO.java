package lk.ijse.preschool.dao.custom;

import lk.ijse.preschool.dao.CrudDAO;
import lk.ijse.preschool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student> {
    public ArrayList<String> getIds() throws SQLException;
}
