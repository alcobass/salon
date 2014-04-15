package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.ServiceType;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ServiceTypeSession {

    private SqlSessionFactory sqlSessionFactory;

    public ServiceTypeSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<ServiceType> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<ServiceType> serviceTypeList = (List<ServiceType>) session
                    .selectList("ServiceType.getAll");
            return serviceTypeList;
        } finally {
            session.close();
        }
    }
}