package lk.ijse.preschool.dao.custom.DAOImpl;

import lk.ijse.preschool.dao.SQLUtil;
import lk.ijse.preschool.dao.custom.EventDAO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.entity.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAOImpl implements EventDAO {
    @Override
    public ArrayList<Event> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM event";

        ArrayList<Event> eveData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            eveData.add(new Event(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return eveData;
    }

    @Override
    public boolean add(Event dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO event(event_no,name,month) VALUES (?,?,?)";

        return SQLUtil.execute(sql,dto.getEvent_no(),dto.getName(),dto.getMonth());    }

    @Override
    public boolean update(Event dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE event SET  name = ?, month = ? WHERE event_no = ?";
        return SQLUtil.execute(sql,dto.getName(),dto.getMonth(),dto.getEvent_no());
    }

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
        String sql = "DELETE FROM Event WHERE event_no = ?";
        return SQLUtil.execute(sql,id);    }

    @Override
    public Event search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM event WHERE event_no = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);

        if(resultSet.next()) {
            String event_event_no = resultSet.getString(1);
            String event_name = resultSet.getString(2);
            String event_month = resultSet.getString(3);


            return new Event(event_event_no, event_name,event_month);
        }
        return null;
    }

 /*   @Override
    public ArrayList<String> getIds() throws SQLException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT * FROM event WHERE event_no = ?";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }*/
}
