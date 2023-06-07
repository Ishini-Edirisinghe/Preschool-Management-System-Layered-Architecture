package lk.ijse.preschool.dao.custom.DAOImpl;

import lk.ijse.preschool.dao.SQLUtil;
import lk.ijse.preschool.dao.custom.PaymentDAO;
import lk.ijse.preschool.db.DBConnection;
import lk.ijse.preschool.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment";
        ArrayList<Payment> payData = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            payData.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        return payData;
    }

    @Override
    public boolean add(Payment dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment(ref_no,date,stid,type) VALUES (?,?,?,?)";

        return SQLUtil.execute(sql,dto.getRef_no() ,dto.getDate() ,dto.getStid() ,dto.getType() );
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE payment SET  date = ?, stid = ?,type=? WHERE ref_no = ?";
        return SQLUtil.execute(sql,dto.getDate(),dto.getStid(),dto.getType() ,dto.getRef_no());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE ref_no = ?";
        return SQLUtil.execute(sql,id);    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE ref_no = ?";
        ResultSet resultSet = SQLUtil.execute(sql, id);

        if(resultSet.next()) {
            String payment_ref_no = resultSet.getString(1);
            String payment_date = resultSet.getString(2);
            String payment_stid = resultSet.getString(3);
            String payment_type = resultSet.getString(4);

            return new Payment(payment_ref_no, payment_date, payment_stid, payment_type);
        }
        return null;    }

    @Override
    public ArrayList<String> getType() throws SQLException {
        ArrayList<String> codes = new ArrayList<>();

        String sql = "SELECT type FROM payment";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public Payment getPaymentType(String ref_no) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE ref_no = ?";

        ResultSet resultSet = SQLUtil.execute(sql,ref_no);
        if (resultSet.next()) {
            return new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;    }
}
