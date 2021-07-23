import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemList implements GoodsMethods{

    private List<Item> itemsList = new ArrayList<>();

    @Override
    //Done
    public Item loadItemById(Integer id) {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Item.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Item result;

        Query query = session.createQuery("from Item where id = :id");
        query.setParameter("id",id);
        result = (Item) query.uniqueResult();
        return result;
    }

    @Override
    //Done
    public void deleteAllOutOfStockItems() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Item.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query deleteQuery = session.createQuery("delete from Item where numberInStock = :num");
        deleteQuery.setParameter("num",0);
        deleteQuery.executeUpdate();

        session.getTransaction().commit();

    }

    @Override
    public List<Item> loadAllAvailableItems() {
        List<Item> availableItems = new ArrayList<>();

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Item.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Item where numberInStock > 0");
        availableItems = query.list();

        return availableItems;

    }

    @Override
    public void saveItem(Item item) {

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Item.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query insertQuery = session.createNativeQuery("insert into Item (partNo,serialNo,name,description,numberInStock,price) values (:partNo,:serialNo,:name,:description,:numberInStock,:price)");
        insertQuery.setParameter("partNo",item.getPartNo());
        insertQuery.setParameter("serialNo",item.getSerialNo());
        insertQuery.setParameter("name",item.getName());
        insertQuery.setParameter("description",item.getDescription());
        insertQuery.setParameter("numberInStock",item.getNumberInStock());
        insertQuery.setParameter("price",item.getPrice());

        insertQuery.executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) {

        Configuration configuration = new Configuration().configure().addAnnotatedClass(Item.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query updateQuery = session.createQuery("update Item set price = :newPrice where id = :id");
        updateQuery.setParameter("newPrice",newPrice);
        updateQuery.setParameter("id",id);

        updateQuery.executeUpdate();
        session.getTransaction().commit();
    }
}
