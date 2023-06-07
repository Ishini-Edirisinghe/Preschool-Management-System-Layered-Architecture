package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existStudent(String id) throws SQLException, ClassNotFoundException;

    public String generateNewStudentId() throws SQLException, ClassNotFoundException;

    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;
    public Boolean PlaceStudent(StudentDTO s1, SkillStatusDTO s2) throws SQLException;
}
