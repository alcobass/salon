package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.Payment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PaymentSession {

    private SqlSessionFactory sqlSessionFactory;

    public PaymentSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<Payment> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<Payment> paymentList = (List<Payment>) session
                    .selectList("payment.getAll");
            return paymentList;
        } finally {
            session.close();
        }
    }
}