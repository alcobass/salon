package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.Registration;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class RegistrationSession {

    private SqlSessionFactory sqlSessionFactory;

    public RegistrationSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<Registration> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<Registration> registrationList = (List<Registration>) session
                    .selectList("payment.getAll");
            return registrationList;
        } finally {
            session.close();
        }
    }
}