package lk.ijse.preschool.model;

import lk.ijse.preschool.DAO.SQLUtil;
import lk.ijse.preschool.dto.EventDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventModel {
    public static boolean save(String event_no, String name, String month) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO event(event_no,name,month) VALUES (?,?,?)";

        return SQLUtil.execute(sql,event_no,name,month);
    }

    public static EventDTO search(String event_no) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM event WHERE event_no = ?";
        ResultSet resultSet = SQLUtil.execute(sql, event_no);

        if(resultSet.next()) {
            String event_event_no = resultSet.getString(1);
            String event_name = resultSet.getString(2);
            String event_month = resultSet.getString(3);


            return new EventDTO(event_event_no, event_name,event_month);
        }
        return null;
    }

    public static boolean deleteEvent(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Event WHERE event_no = ?";
        return SQLUtil.execute(sql,code);
    }

    public static boolean update(String event_no, String name, String month) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE event SET  name = ?, month = ? WHERE event_no = ?";
        return SQLUtil.execute(sql,name,month,event_no);

    }

    public static List<EventDTO> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM event";

        List<EventDTO> eveData = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            eveData.add(new EventDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            ));
        }
        return eveData;
    }
}
