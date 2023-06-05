package lk.ijse.preschool.model;

import lk.ijse.preschool.bo.BOFactory;
import lk.ijse.preschool.bo.costom.StudentBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.SkillStatusDTO;
import lk.ijse.preschool.dto.StudentDTO;

import java.sql.SQLException;


public class PlaceStudentModel {
    private static StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public static Boolean PlaceStudent(StudentDTO s1, SkillStatusDTO s2) throws SQLException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean save = studentBO.addStudent(new StudentDTO(s1.getStId(), s1.getName(),s1.getAddress(),s1.getDOB(),s1.getContact(),s1.getParentName(),s1.getTeachId()));
            if (save){
              //  System.out.println("Done 1");
                boolean saveSkills = SkillStatusModel.save(s2.getStid(), s2.getStName(), s2.getCounting(), s2.getCrafting(),s2.getDrawing(), s2.getReading(), s2.getSinging(), s2.getWriting());
                if (saveSkills){
               //     System.out.println("Done 2");
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
        return null;
    }
}
