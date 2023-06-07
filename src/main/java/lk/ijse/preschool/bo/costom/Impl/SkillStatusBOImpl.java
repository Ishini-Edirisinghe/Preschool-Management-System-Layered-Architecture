package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.dao.DAOFactory;
import lk.ijse.preschool.dao.custom.SkillStatusDAO;
import lk.ijse.preschool.bo.costom.SkillStatusBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.entity.SkillStatus;
import lk.ijse.preschool.entity.Student;
import lk.ijse.preschool.entity.Syllabus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SkillStatusBOImpl implements SkillStatusBO {
    private SkillStatusDAO skillStatusDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SKILLSTATUS);



    @Override
    public boolean update(SkillStatusDTO dto) throws SQLException, ClassNotFoundException {
        return skillStatusDAO.update(new SkillStatus(dto.getStid(),dto.getStName() ,dto.getCounting() ,dto.getCrafting() ,dto.getDrawing() ,dto.getReading() ,dto.getSinging(),dto.getWriting()));
    }

    @Override
    public SkillStatusDTO search(String id) throws SQLException, ClassNotFoundException {
        SkillStatus skillStatus = skillStatusDAO.search(id);
        return new SkillStatusDTO(skillStatus.getStid(),skillStatus.getStName() ,skillStatus.getCounting() ,skillStatus.getCrafting() ,skillStatus.getDrawing() ,skillStatus.getReading() ,skillStatus.getSinging(),skillStatus.getWriting());     }

    @Override
    public ArrayList<String> getStatus() throws SQLException {
        ArrayList<String> SkillStatusAll = skillStatusDAO.getStatus ();


        return SkillStatusAll;
    }

    @Override
    public SkillStatusDTO searchByIdGetSkills(String studentId) throws SQLException, ClassNotFoundException {
        SkillStatus skillStatus=skillStatusDAO.searchByIdGetSkills(studentId);
        return new SkillStatusDTO(skillStatus.getStid(),skillStatus.getStName() ,skillStatus.getCounting() ,skillStatus.getCrafting() ,skillStatus.getDrawing() ,skillStatus.getReading() ,skillStatus.getSinging(),skillStatus.getWriting());
    }

    @Override
    public int getStatusCount(String subject, String status) throws SQLException, ClassNotFoundException {
        return skillStatusDAO.getStatusCount(subject,status);
    }

    @Override
    public boolean add(SkillStatusDTO dto) throws SQLException, ClassNotFoundException {
        return skillStatusDAO.add(new SkillStatus(dto.getStid(),dto.getStName() ,dto.getCounting() ,dto.getCrafting() ,dto.getDrawing() ,dto.getReading() ,dto.getSinging(),dto.getWriting()));
    }
}
