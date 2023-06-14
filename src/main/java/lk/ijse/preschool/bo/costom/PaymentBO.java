package lk.ijse.preschool.bo.costom;

import lk.ijse.preschool.bo.SuperBO;
import lk.ijse.preschool.dto.PaymentDTO;
import lk.ijse.preschool.dto.SyllabusDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO  extends SuperBO {
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException;

  //  public ArrayList<String> getIds() throws SQLException, ClassNotFoundException;

    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existPayment(String id) throws SQLException, ClassNotFoundException;

    public String generateNewPaymentId() throws SQLException, ClassNotFoundException;

    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException;
}
