package lk.ijse.preschool.bo.costom.Impl;

import lk.ijse.preschool.DAO.DAOFactory;
import lk.ijse.preschool.DAO.custom.PaymentDAO;
import lk.ijse.preschool.DAO.custom.SyllabusDAO;
import lk.ijse.preschool.bo.costom.PaymentBO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.dto.PaymentDTO;
import lk.ijse.preschool.dto.SyllabusDTO;
import lk.ijse.preschool.entity.Payment;
import lk.ijse.preschool.entity.Syllabus;

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
    public ArrayList<String> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT * FROM payment WHERE ref_no = ?";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;    }

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
