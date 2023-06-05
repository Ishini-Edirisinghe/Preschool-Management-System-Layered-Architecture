package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;
import lk.ijse.preschool.dto.EventDTO;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.entity.Syllabus;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SyllabusBO extends SuperBO {
    public ArrayList<SyllabusDTO> getAllSyllabus() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    public boolean addSyllabus(SyllabusDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateSyllabus(SyllabusDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existSyllabus(String id) throws SQLException, ClassNotFoundException;

    public String generateNewSyllabusId() throws SQLException, ClassNotFoundException;

    public boolean deleteSyllabus(String id) throws SQLException, ClassNotFoundException;

    public SyllabusDTO searchSyllabus(String id) throws SQLException, ClassNotFoundException;
}
