package lk.ijse.preschool.dao.custom.DAOImpl;

import lk.ijse.preschool.dao.SQLUtil;
import lk.ijse.preschool.dao.custom.SkillStatusDAO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.entity.SkillStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillStatusDAOImpl implements SkillStatusDAO {
    @Override
    public ArrayList<SkillStatus> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean add(SkillStatus entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO student_skill_status(stId,stName,counting,crafting,drawing,reading,singing,writing) VALUES (?,?,?,?,?,?,?,?)";

        return SQLUtil.execute(sql,entity.getStid() ,entity.getStName() ,entity.getCounting() ,entity.getCrafting() ,entity.getDrawing() ,entity.getReading() ,entity.getSinging(),entity.getWriting() );
    }

    @Override
    public boolean update(SkillStatus dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE student_skill_status SET stName = ?, counting = ? ,crafting =? , drawing=?,reading=?,singing=?,writing=? WHERE stId = ?";
        return SQLUtil.execute(sql,dto.getStName() ,dto.getCounting() ,dto.getCrafting() ,dto.getDrawing() ,dto.getReading() ,dto.getSinging(),dto.getWriting(),dto.getStid() );    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet");
    }

    @Override
    public SkillStatus search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM student_skill_status WHERE stId = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);
        if (resultSet.next()) {
            return new SkillStatus(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)

            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getStatus() throws SQLException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT status FROM student_skill_status";
        ResultSet resultSet =  DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while (resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public SkillStatus searchByIdGetSkills(String studentId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT stName,counting,crafting,drawing,reading,singing,writing FROM student_skill_status WHERE stId = ?";


        ResultSet resultSet = SQLUtil.execute(sql, studentId);
        if (resultSet.next()) {
            return new SkillStatus(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)

            );
        }
        return null;
    }

    @Override
    public int getStatusCount(String subject, String status) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS statusCount FROM student_skill_status WHERE "+subject+"=?";
        ResultSet resultSet=SQLUtil.execute(sql,status);
        String string = null;
        while (resultSet.next()) {
            string= resultSet.getString(1);
        }
        return Integer.parseInt(string);
    }
}
