package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;
import lk.ijse.preschool.dto.StudentDTO;
import lk.ijse.preschool.dto.TeacherDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherBo extends SuperBO {
    public ArrayList<TeacherDTO> getAllTeachers() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    public boolean addTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existTeacher(String id) throws SQLException, ClassNotFoundException;

    public String generateNewTeacherId() throws SQLException, ClassNotFoundException;

    public boolean deleteTeacher(String id) throws SQLException, ClassNotFoundException;

    public TeacherDTO searchTeacher(String id) throws SQLException, ClassNotFoundException;
}
