package lk.ijse.preschool.DAO.custom.DAOImpl;

import lk.ijse.preschool.DAO.SQLUtil;
import lk.ijse.preschool.DAO.custom.StudentDAO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.StudentDTO;
import lk.ijse.preschool.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM student";

        ArrayList<Student> stuData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            stuData.add(new Student(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return stuData;
    }

    @Override
    public boolean add(Student dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO student(stid,name,address,DOB,contact,parentsName,teachId) VALUES (?,?,?,?,?,?,?)";

        return SQLUtil.execute(sql, dto.getStId(), dto.getName(), dto.getAddress(), dto.getDOB(),dto.getContact(),dto.getParentName(),dto.getTeachId());
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE student SET  name = ?, address = ?,DOB=?,contact=?,parentsName=?,teachId=? WHERE stid = ?";
        return SQLUtil.execute(sql,dto.getName(), dto.getAddress(),dto.getDOB(), dto.getContact(),dto.getParentName(),dto.getTeachId(),dto.getStId());    }

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
        String sql = "DELETE FROM student WHERE stid = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM student WHERE stid = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);

        if(resultSet.next()) {
            String student_stid = resultSet.getString(1);
            String student_name = resultSet.getString(2);
            String student_address = resultSet.getString(3);
            String student_DOB = resultSet.getString(4);
            String student_contact = resultSet.getString(5);
            String student_parentsName = resultSet.getString(6);
            String teachId = resultSet.getString(7);

            return new Student(student_stid, student_name, student_address, student_DOB,student_contact,student_parentsName,teachId);
        }
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException {

        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT stid FROM student";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }
}
