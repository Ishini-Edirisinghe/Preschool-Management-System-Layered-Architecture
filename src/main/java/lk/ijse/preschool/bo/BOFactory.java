package lk.ijse.preschool.bo;

import lk.ijse.preschool.bo.costom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT,TEACHER,EVENT,SYLLABUS,PAYMENT,SKILLSTATUS
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
            case SKILLSTATUS:
                return (T)new SkillStatusBOImpl();
            default:
                return null;
        }
    }
}
