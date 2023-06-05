package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.DAO.DAOFactory;
import lk.ijse.preschool.DAO.custom.StudentDAO;
import lk.ijse.preschool.bo.costom.StudentBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.StudentDTO;
import lk.ijse.preschool.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    private StudentDAO studentDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student> StudentsAll = studentDAO.getAll();
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : StudentsAll) {
            studentDTOS.add(new StudentDTO(student.getStId(), student.getName(), student.getAddress(),student.getDOB(),student.getContact(),student.getParentName(),student.getTeachId()));
        }
        return studentDTOS;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT stid FROM student";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public boolean addStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.add(new Student(dto.getStId(), dto.getName(), dto.getAddress(),dto.getDOB(),dto.getContact(),dto.getParentName(),dto.getTeachId()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getStId(), dto.getName(), dto.getAddress(),dto.getDOB(),dto.getContact(),dto.getParentName(),dto.getTeachId()));

    }

    @Override
    public boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.exist(id);
    }

    @Override
    public String generateNewStudentId() throws SQLException, ClassNotFoundException {
        return studentDAO.generateNewId();
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.search(id);
        return new StudentDTO(student.getStId(), student.getName(), student.getAddress(),student.getDOB(),student.getContact(),student.getParentName(),student.getTeachId());
    }
}
