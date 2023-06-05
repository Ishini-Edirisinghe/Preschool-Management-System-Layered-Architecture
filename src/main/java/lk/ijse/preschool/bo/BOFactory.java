package lk.ijse.preschool.bo;

import lk.ijse.preschool.DAO.DAOFactory;
import lk.ijse.preschool.DAO.SuperDAO;
import lk.ijse.preschool.DAO.custom.DAOImpl.StudentDAOImpl;
import lk.ijse.preschool.DAO.custom.DAOImpl.TeacherDAOImpl;
import lk.ijse.preschool.bo.costom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT,TEACHER,EVENT,SYLLABUS,PAYMENT
    }

    public <T> T  getBO(BOTypes boTypes) {
        switch (boTypes) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case TEACHER:
                return (T) new TeacherBOImpl();
            case EVENT:
                return (T) new EventBOImpl();
            case SYLLABUS:
                return (T) new SyllabusBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
           /*   case QUERY_DAO:
                return new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}
