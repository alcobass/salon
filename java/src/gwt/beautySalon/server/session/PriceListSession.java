package gwt.beautySalon.server.session;

import gwt.beautySalon.server.ConnectionFactory;
import gwt.beautySalon.shared.PriceList;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PriceListSession {
    private SqlSessionFactory sqlSessionFactory;

    public PriceListSession() {
        sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    @SuppressWarnings("unchecked")
    public List<PriceList> selectAll() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            List<PriceList> priceList = (List<PriceList>) session
                    .selectList("priceList.getAll");
            return priceList;
        } finally {
            session.close();
        }
    }

    public void insertPriceList(PriceList priceList) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.insert("priceList.insert", priceList);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void updatePriceList(PriceList priceList) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.insert("priceList.update", priceList);
            session.commit();
        } finally {
            session.close();
        }
    }

    public void deletePriceList(int id) {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.insert("priceList.delete", id);
            session.commit();
        } finally {
            session.close();
        }
    }

}