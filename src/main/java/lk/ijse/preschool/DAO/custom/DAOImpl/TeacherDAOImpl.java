package lk.ijse.preschool.DAO.custom.DAOImpl;

import lk.ijse.preschool.DAO.SQLUtil;
import lk.ijse.preschool.DAO.custom.TeacherDAO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.TeacherDTO;
import lk.ijse.preschool.entity.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM teacher";

        ArrayList<Teacher> teaData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            teaData.add(new Teacher(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }
        return teaData;
    }

    @Override
    public boolean add(Teacher dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO teacher(teachId,name,address,DOB,contact) VALUES (?,?,?,?,?)";

        return SQLUtil.execute(sql, dto.getTeachId(), dto.getName(), dto.getAddress(), dto.getDOB(),dto.getContact());
    }

    @Override
    public boolean update(Teacher dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE teacher SET name = ?, address = ?,DOB=?,contact=? WHERE teachId = ?";
        return SQLUtil.execute(sql,dto.getName(),dto.getAddress(),dto.getDOB(),dto.getContact(),dto.getTeachId());
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
        String sql = "DELETE FROM teacher WHERE teachId = ?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public Teacher search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM teacher WHERE teachId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);

        if(resultSet.next()) {
            String teacher_teachId = resultSet.getString(1);
            String teacher_name = resultSet.getString(2);
            String teacher_address = resultSet.getString(3);
            String teacher_DOB = resultSet.getString(4);
            String teacher_contact = resultSet.getString(5);


            return new Teacher(teacher_teachId, teacher_name, teacher_address, teacher_DOB,teacher_contact);
        }
        return null;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT teachId FROM teacher";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }
}
