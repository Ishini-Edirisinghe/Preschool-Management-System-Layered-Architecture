package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.dao.DAOFactory;
import lk.ijse.preschool.dao.custom.PaymentDAO;
import lk.ijse.preschool.bo.costom.PaymentBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.PaymentDTO;
import lk.ijse.preschool.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    private PaymentDAO paymentDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> PaymentAll = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : PaymentAll) {
            paymentDTOS.add(new PaymentDTO(payment.getRef_no(),payment.getDate(),payment.getStid(),payment.getType()));
        }
        return paymentDTOS;
    }


    @Override
    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getRef_no(), dto.getDate(), dto.getStid(),dto.getType()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getRef_no(), dto.getDate(), dto.getStid(),dto.getType()));
    }

    @Override
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.exist(id);
    }

    @Override
    public String generateNewPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewId();
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.search(id);
        return new PaymentDTO(payment.getRef_no(), payment.getDate(), payment.getStid(),payment.getType());
    }
}
