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
                    .selectList("serviceType.getAll");
            return serviceTypeList;
        } finally {
            session.close();
        }
    }

    public void insertServiceType(ServiceType newServiceType) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.insert("serviceType.insert", newServiceType);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void deleteServiceType(int id) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("serviceType.delete", id);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void updateServicetype(ServiceType updateServiceType) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("serviceType.update", updateServiceType);
            session.commit();
        } finally {
            session.close();
        }
    }
}