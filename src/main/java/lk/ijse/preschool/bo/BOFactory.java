package lk.ijse.preschool.bo;

import lk.ijse.preschool.DAO.DAOFactory;
import lk.ijse.preschool.DAO.SuperDAO;
import lk.ijse.preschool.DAO.custom.DAOImpl.StudentDAOImpl;
import lk.ijse.preschool.DAO.custom.DAOImpl.TeacherDAOImpl;
import lk.ijse.preschool.bo.costom.Impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT,TEACHER
    }

    public <T> T  getBO(BOTypes boTypes) {
        switch (boTypes) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case TEACHER:
                return (T) new TeacherDAOImpl();
       /*   case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();*/
            default:
                return null;
        }
    }
}
