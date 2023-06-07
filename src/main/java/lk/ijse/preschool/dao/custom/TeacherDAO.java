package lk.ijse.preschool.dao.custom;

import lk.ijse.preschool.dao.CrudDAO;
import lk.ijse.preschool.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherDAO extends CrudDAO<Teacher> {
    public ArrayList<String> getIds() throws SQLException;

}
