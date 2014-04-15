package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.PaymentMethod;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PaymentMethodSession {

    private SqlSessionFactory sqlSessionFactory;

    public PaymentMethodSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<PaymentMethod> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<PaymentMethod> paymentMethodList = (List<PaymentMethod>) session
                    .selectList("PaymentMethod.getAll");
            return paymentMethodList;
        } finally {
            session.close();
        }
    }
}