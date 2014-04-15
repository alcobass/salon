package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.PaymentType;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PaymentTypeSession {

    private SqlSessionFactory sqlSessionFactory;

    public PaymentTypeSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<PaymentType> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<PaymentType> paymentTypeList = (List<PaymentType>) session
                    .selectList("PaymentType.getAll");
            return paymentTypeList;
        } finally {
            session.close();
        }
    }
}