package lk.ijse.preschool.DAO.custom;

import lk.ijse.preschool.DAO.CrudDAO;
import lk.ijse.preschool.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherDAO extends CrudDAO<Teacher> {
    public ArrayList<String> getIds() throws SQLException;

}
