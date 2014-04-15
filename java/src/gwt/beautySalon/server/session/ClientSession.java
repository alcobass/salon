package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.Client;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ClientSession {

    private SqlSessionFactory sqlSessionFactory;

    public ClientSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<Client> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<Client> clientList = (List<Client>) session
                    .selectList("client.getAll");
            return clientList;
        } finally {
            session.close();
        }
    }
}