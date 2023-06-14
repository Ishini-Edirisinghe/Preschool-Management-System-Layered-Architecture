package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.dao.DAOFactory;
import lk.ijse.preschool.dao.custom.EventDAO;
import lk.ijse.preschool.bo.costom.EventBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.EventDTO;
import lk.ijse.preschool.entity.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventBOImpl implements EventBO {
    private EventDAO eventDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EVENT);
    @Override
    public ArrayList<EventDTO> getAllEvents() throws SQLException, ClassNotFoundException {
        ArrayList<Event> eventsAll = eventDAO.getAll();
        ArrayList<EventDTO> eventDTOS = new ArrayList<>();
        for (Event event : eventsAll) {
            eventDTOS.add(new EventDTO(event.getEvent_no(), event.getName(), event.getMonth()));
        }
        return eventDTOS;
    }

    @Override
    public boolean addEvent(EventDTO dto) throws SQLException, ClassNotFoundException {
        return eventDAO.add(new Event(dto.getEvent_no(), dto.getName(), dto.getMonth()));
    }

    @Override
    public boolean updateEvent(EventDTO dto) throws SQLException, ClassNotFoundException {
        return eventDAO.update(new Event(dto.getEvent_no(), dto.getName(), dto.getMonth()));
    }

    @Override
    public boolean existEvent(String id) throws SQLException, ClassNotFoundException {
        return eventDAO.exist(id);
    }

    @Override
    public String generateNewEventId() throws SQLException, ClassNotFoundException {
        return eventDAO.generateNewId();
    }

    @Override
    public boolean deleteEvent(String id) throws SQLException, ClassNotFoundException {
        return eventDAO.delete(id);
    }

    @Override
    public EventDTO searchEvent(String id) throws SQLException, ClassNotFoundException {
        Event event = eventDAO.search(id);
        return new EventDTO(event.getEvent_no(), event.getName(), event.getMonth());
    }
}
