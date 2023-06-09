package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.bo.costom.LoginBO;
import lk.ijse.preschool.dao.DAOFactory;
import lk.ijse.preschool.dao.custom.LoginDAO;
import lk.ijse.preschool.dao.custom.SkillStatusDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private LoginDAO loginDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean userCheckedInDB(String username, String password) throws SQLException, ClassNotFoundException {
        return loginDAO.userCheckedInDB(username,password);
    }
}
