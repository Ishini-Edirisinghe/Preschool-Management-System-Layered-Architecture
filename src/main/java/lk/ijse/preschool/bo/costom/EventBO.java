package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;
import lk.ijse.preschool.dto.EventDTO;
import lk.ijse.preschool.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EventBO extends SuperBO {
    public ArrayList<EventDTO> getAllEvents() throws SQLException, ClassNotFoundException;

   // public ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    public boolean addEvent(EventDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateEvent(EventDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existEvent(String id) throws SQLException, ClassNotFoundException;

    public String generateNewEventId() throws SQLException, ClassNotFoundException;

    public boolean deleteEvent(String id) throws SQLException, ClassNotFoundException;

    public EventDTO searchEvent(String id) throws SQLException, ClassNotFoundException;
}
