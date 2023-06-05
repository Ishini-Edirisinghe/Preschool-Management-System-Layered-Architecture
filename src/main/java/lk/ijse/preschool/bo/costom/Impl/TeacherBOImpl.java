package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.DAO.DAOFactory;
import lk.ijse.preschool.DAO.custom.StudentDAO;
import lk.ijse.preschool.DAO.custom.TeacherDAO;
import lk.ijse.preschool.bo.costom.TeacherBo;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.StudentDTO;
import lk.ijse.preschool.dto.TeacherDTO;
import lk.ijse.preschool.entity.Student;
import lk.ijse.preschool.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherBOImpl implements TeacherBo {
    private TeacherDAO teacherDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TEACHER);

    @Override
    public ArrayList<TeacherDTO> getAllTeachers() throws SQLException, ClassNotFoundException {
        ArrayList<Teacher> TeachersAll = teacherDAO.getAll();
        ArrayList<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher teacher : TeachersAll) {
            teacherDTOS.add(new TeacherDTO(teacher.getTeachId(), teacher.getName(), teacher.getAddress(),teacher.getDOB(),teacher.getContact()));
        }
        return teacherDTOS;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT teachId FROM teacher";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public boolean addTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.add(new Teacher(dto.getTeachId(), dto.getName(), dto.getAddress(),dto.getDOB(),dto.getContact()));
    }

    @Override
    public boolean updateTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.update(new Teacher(dto.getTeachId(), dto.getName(), dto.getAddress(),dto.getDOB(),dto.getContact()));
    }

    @Override
    public boolean existTeacher(String id) throws SQLException, ClassNotFoundException {
        return teacherDAO.exist(id);
    }

    @Override
    public String generateNewTeacherId() throws SQLException, ClassNotFoundException {
        return teacherDAO.generateNewId();
    }

    @Override
    public boolean deleteTeacher(String id) throws SQLException, ClassNotFoundException {
        return teacherDAO.delete(id);
    }

    @Override
    public TeacherDTO searchTeacher(String id) throws SQLException, ClassNotFoundException {
        Teacher teacher = teacherDAO.search(id);
        return new TeacherDTO(teacher.getTeachId(), teacher.getName(), teacher.getAddress(),teacher.getDOB(),teacher.getContact());
    }
}
