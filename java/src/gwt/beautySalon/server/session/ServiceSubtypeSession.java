package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.ServiceSubtype;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ServiceSubtypeSession {

    private SqlSessionFactory sqlSessionFactory;

    public ServiceSubtypeSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<ServiceSubtype> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<ServiceSubtype> serviceSubtypeList = (List<ServiceSubtype>) session
                    .selectList("serviceSubtype.getAll");
            return serviceSubtypeList;
        } finally {
            session.close();
        }
    }
}