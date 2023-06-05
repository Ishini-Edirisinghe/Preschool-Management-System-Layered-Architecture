package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.DAO.DAOFactory;
import lk.ijse.preschool.DAO.custom.EventDAO;
import lk.ijse.preschool.DAO.custom.SyllabusDAO;
import lk.ijse.preschool.bo.costom.SyllabusBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.EventDTO;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.entity.Event;
import lk.ijse.preschool.entity.Syllabus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SyllabusBOImpl implements SyllabusBO {
    private SyllabusDAO syllabusDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SYLLABUS);

    @Override
    public ArrayList<SyllabusDTO> getAllSyllabus() throws SQLException, ClassNotFoundException {
        ArrayList<Syllabus> SyllabusAll = syllabusDAO.getAll();
        ArrayList<SyllabusDTO> syllabusDTOS = new ArrayList<>();
        for (Syllabus syllabus : SyllabusAll) {
            syllabusDTOS.add(new SyllabusDTO(syllabus.getSubject_id(),syllabus.getSub_name()));
        }
        return syllabusDTOS;
    }

    @Override
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT * FROM syllabus WHERE subject_id = ?";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public boolean addSyllabus(SyllabusDTO dto) throws SQLException, ClassNotFoundException {
        return syllabusDAO.add(new Syllabus(dto.getSubject_id(), dto.getSub_name()));
    }

    @Override
    public boolean updateSyllabus(SyllabusDTO dto) throws SQLException, ClassNotFoundException {
        return syllabusDAO.update(new Syllabus(dto.getSubject_id(), dto.getSub_name()));
    }

    @Override
    public boolean existSyllabus(String id) throws SQLException, ClassNotFoundException {
        return syllabusDAO.exist(id);
    }

    @Override
    public String generateNewSyllabusId() throws SQLException, ClassNotFoundException {
        return syllabusDAO.generateNewId();
    }

    @Override
    public boolean deleteSyllabus(String id) throws SQLException, ClassNotFoundException {
        return syllabusDAO.delete(id);
    }

    @Override
    public SyllabusDTO searchSyllabus(String id) throws SQLException, ClassNotFoundException {
        Syllabus syllabus = syllabusDAO.search(id);
        return new SyllabusDTO(syllabus.getSubject_id(), syllabus.getSub_name());    }
}
