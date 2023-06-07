package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.entity.SkillStatus;
import lk.ijse.preschool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SkillStatusBO extends SuperBO {
    public boolean update(SkillStatusDTO dto)throws SQLException, ClassNotFoundException;
    public SkillStatusDTO  search(String id)throws SQLException, ClassNotFoundException;

    public  ArrayList<String> getStatus() throws SQLException;
    public  SkillStatusDTO searchByIdGetSkills(String studentId) throws SQLException, ClassNotFoundException;
    public  int getStatusCount(String subject,String status) throws SQLException, ClassNotFoundException;
    public boolean add(SkillStatusDTO dto) throws SQLException, ClassNotFoundException;
}
