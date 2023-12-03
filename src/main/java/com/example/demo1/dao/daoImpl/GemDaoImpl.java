package com.example.demo1.dao.daoImpl;

import com.example.demo1.dao.GemDao;
import com.example.demo1.entity.Gem;
import com.example.demo1.sessionFactory.SessionFactoryImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class GemDaoImpl implements GemDao {

    @Override
    public boolean addGem(Gem gem) {
        boolean isAdded = false;
        Session session = null;
        try {
            session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Gem> cr = cb.createQuery(Gem.class);
            Root<Gem> root = cr.from(Gem.class);
            cr.select(root).where(cb.equal(root.get("name"), gem.getName()));
            List<Gem> gems = session.createQuery(cr).getResultList();

            if (gems.isEmpty()) {
                session.save(gem);
                tx.commit();
                isAdded = true;
            } else {
                System.out.println("User with this login already exists.");
            }


        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }finally {
            session.close();
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
        List<Gem> gems = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Gem> cr = cb.createQuery(Gem.class);
            Root<Gem> root = cr.from(Gem.class);
            cr.select(root);
            gems = session.createQuery(cr).getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return gems;
    }

    @Override
    public List<Gem> showSortedByPriceGem() {
        List<Gem> gems = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Gem> cr = cb.createQuery(Gem.class);
            Root<Gem> root = cr.from(Gem.class);
            cr.select(root);
            cr.orderBy(cb.asc(root.get("price"))); // Сортировка по возрастанию цены
            gems = session.createQuery(cr).getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return gems;
    }

    @Override
    public List<Gem> showSortedByWeightGem() {
        List<Gem> gems = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Gem> cr = cb.createQuery(Gem.class);
            Root<Gem> root = cr.from(Gem.class);
            cr.select(root);
            cr.orderBy(cb.asc(root.get("weight"))); // Сортировка по возрастанию веса
            gems = session.createQuery(cr).getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
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
