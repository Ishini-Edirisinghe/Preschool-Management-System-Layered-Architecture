package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.dao.DAOFactory;
import lk.ijse.preschool.dao.custom.SyllabusDAO;
import lk.ijse.preschool.bo.costom.SyllabusBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.StudentDTO;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.entity.Student;
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
    public ArrayList<SyllabusDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Syllabus> SyllabusAll = syllabusDAO.getAll();
        ArrayList<SyllabusDTO> syllabusDTOS = new ArrayList<>();
        for (Syllabus syllabus : SyllabusAll) {
            syllabusDTOS.add(new SyllabusDTO(syllabus.getSubject_id(),syllabus.getSub_name()));
        }
        return syllabusDTOS;
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
