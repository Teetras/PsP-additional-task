package com.example.dao.daoImpl;

import com.example.dao.GemDao;
import com.example.entity.User;
import com.example.sessionFactory.SessionFactoryImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.example.entity.Gem;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class GemDaoImpl implements GemDao {

    @Override
    public boolean addGem(Gem gem) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(gem);
            tx.commit();
            session.close();
            isAdded = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateGem(Gem gem) {
        boolean isUpdated = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(gem);
            tx.commit();
            session.close();
            isUpdated = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteGem(int id) {
        boolean isDeleted = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Gem gem = session.load(Gem.class, id);
            session.delete(gem);
            tx.commit();
            session.close();
            isDeleted = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public List<Gem> showGem() {
        List<Gem> gems = (List<Gem>) SessionFactoryImpl.getSessionFactory().openSession().createQuery("FROM Gem").list();
        return gems;
    }

    @Override
    public List<Gem> showSortedByPriceGem() {
        List<Gem> gems = (List<Gem>) SessionFactoryImpl.getSessionFactory().openSession().createQuery("FROM Gem ORDER BY price").list();
        return gems;
    }

    @Override
    public List<Gem> showSortedByWeightGem() {
        List<Gem> gems = (List<Gem>) SessionFactoryImpl.getSessionFactory().openSession().createQuery("FROM Gem ORDER BY weight").list();
        return gems;
    }

    @Override
    public List<Gem> showDiapasonOpacityGem(double startOpacity, double endOpacity) {
        SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Gem> criteriaQuery = criteriaBuilder.createQuery(Gem.class);
            Root<Gem> root = criteriaQuery.from(Gem.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.between(root.get("opacity"), startOpacity, endOpacity));

            Query<Gem> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            System.out.println("HibernateException:" + e);
        }
        return Collections.emptyList(); // Возвращаем пустой список, если что-то пошло не так
    }

    @Override
    public Gem findGemById(int id) {
        Gem gem = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Gem> cr = cb.createQuery(Gem.class);
            Root<Gem> root = cr.from(Gem.class);
            cr.select(root).where(cb.equal(root.get("testId"), id));
            gem = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return gem;
    }

    @Override
    public Gem findGemByName(String name) {
        Gem gem = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Gem> cr = cb.createQuery(Gem.class);
            Root<Gem> root = cr.from(Gem.class);
            cr.select(root).where(cb.equal(root.get("name"), name));
            gem = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return gem;
    }


}
