package lk.ijse.preschool.dao;

import lk.ijse.preschool.dao.custom.DAOImpl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT,TEACHER,EVENT,SYLLABUS,PAYMENT,SKILLSTATUS
    }

    public <T> T  getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case TEACHER:
                return (T) new TeacherDAOImpl();
            case EVENT:
                return (T) new EventDAOImpl();
            case SYLLABUS:
                return (T) new SyllabusDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case SKILLSTATUS:
                return (T)new SkillStatusDAOImpl();
            default:
                return null;
        }
    }
}
