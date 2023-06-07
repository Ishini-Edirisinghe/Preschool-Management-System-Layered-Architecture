package lk.ijse.preschool.dao.custom;

import lk.ijse.preschool.dao.CrudDAO;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.entity.SkillStatus;
import lk.ijse.preschool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SkillStatusDAO extends CrudDAO<SkillStatus> {
    public  ArrayList<String> getStatus() throws SQLException;
    public  SkillStatus searchByIdGetSkills(String studentId) throws SQLException, ClassNotFoundException;
    public  int getStatusCount(String subject,String status) throws SQLException, ClassNotFoundException;

}
