package lk.ijse.preschool.dao.custom.DAOImpl;

import lk.ijse.preschool.dao.SQLUtil;
import lk.ijse.preschool.dao.custom.SyllabusDAO;
import lk.ijse.preschool.entity.Syllabus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SyllabusDAOImpl implements SyllabusDAO {
    @Override
    public ArrayList<Syllabus> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM syllabus";

        ArrayList<Syllabus> syllaData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            syllaData.add(new Syllabus(
                    resultSet.getString(1),
                    resultSet.getString(2)

            ));
        }
        return syllaData;    }

    @Override
    public boolean add(Syllabus dto) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO syllabus(subject_id,sub_name) VALUES (?,?)";

        return SQLUtil.execute(sql, dto.getSubject_id(), dto.getSub_name());    }

    @Override
    public boolean update(Syllabus dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE syllabus SET  sub_name=? WHERE subject_id = ?";
        return SQLUtil.execute(sql,dto.getSub_name(),dto.getSubject_id());    }

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
        String sql = "DELETE FROM syllabus WHERE subject_id = ?";
        return SQLUtil.execute(sql,id);    }

    @Override
    public Syllabus search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM syllabus WHERE subject_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);

        if(resultSet.next()) {
            String syllabus_id = resultSet.getString(1);
            String syllabus_sub_name = resultSet.getString(2);
            String  stid= resultSet.getString(3);




            return new Syllabus(syllabus_id,syllabus_sub_name);
        }
        return null;    }
}
