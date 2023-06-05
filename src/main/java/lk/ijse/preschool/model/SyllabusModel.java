package lk.ijse.preschool.model;

import lk.ijse.preschool.DAO.SQLUtil;
import lk.ijse.preschool.dto.SyllabusDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SyllabusModel {
    public static boolean save(String subject_id, String sub_name) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO syllabus(subject_id,sub_name) VALUES (?,?)";

        return SQLUtil.execute(sql, subject_id, sub_name);

    }

    public static SyllabusDTO search(String subject_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM syllabus WHERE subject_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, subject_id);

        if(resultSet.next()) {
            String syllabus_id = resultSet.getString(1);
            String syllabus_sub_name = resultSet.getString(2);
            String  stid= resultSet.getString(3);




            return new SyllabusDTO(syllabus_id,syllabus_sub_name,stid);
        }
        return null;
    }

    public static boolean update(String subject_id, String sub_name) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE syllabus SET  sub_name=? WHERE subject_id = ?";
        return SQLUtil.execute(sql,sub_name,subject_id);

    }

    public static boolean deleteSyllabus(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM syllabus WHERE subject_id = ?";
        return SQLUtil.execute(sql,code);
    }

    public static List<SyllabusDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM syllabus";

        List<SyllabusDTO> syllaData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            syllaData.add(new SyllabusDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return syllaData;
    }
}
