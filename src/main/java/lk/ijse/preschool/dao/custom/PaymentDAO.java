package lk.ijse.preschool.dao.custom;

import lk.ijse.preschool.dao.CrudDAO;
import lk.ijse.preschool.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    public ArrayList<String> getType() throws SQLException;
    public Payment getPaymentType(String ref_no) throws SQLException, ClassNotFoundException;
}
