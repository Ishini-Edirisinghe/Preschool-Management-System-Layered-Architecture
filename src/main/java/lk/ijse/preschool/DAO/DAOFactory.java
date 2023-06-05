package lk.ijse.preschool.DAO;

import lk.ijse.preschool.DAO.custom.DAOImpl.StudentDAOImpl;
import lk.ijse.preschool.DAO.custom.DAOImpl.TeacherDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT,TEACHER
    }

    public <T> T  getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case TEACHER:
                return (T) new TeacherDAOImpl();
           /* case ORDER:
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
