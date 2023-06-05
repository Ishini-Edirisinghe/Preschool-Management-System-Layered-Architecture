package lk.ijse.preschool.DAO.custom;

import lk.ijse.preschool.DAO.CrudDAO;
import lk.ijse.preschool.dto.PaymentDTO;
import lk.ijse.preschool.entity.Event;
import lk.ijse.preschool.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    public ArrayList<String> getType() throws SQLException;
    public Payment getPaymentType(String ref_no) throws SQLException, ClassNotFoundException;
}
