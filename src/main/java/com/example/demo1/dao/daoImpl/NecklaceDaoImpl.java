package com.example.demo1.dao.daoImpl;

import com.example.demo1.dao.NecklaceDao;
import com.example.demo1.entity.Necklace;
import com.example.demo1.sessionFactory.SessionFactoryImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class NecklaceDaoImpl implements NecklaceDao {

    @Override
    public boolean addNecklace(Necklace necklace) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(necklace);
            tx.commit();
            session.close();
            isAdded = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateNecklace(Necklace necklace) {
        boolean isUpdated = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(necklace);
            tx.commit();
            session.close();
            isUpdated = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteNecklace(int id) {
        boolean isDeleted = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Necklace necklace = session.load(Necklace.class, id);
            session.delete(necklace);
            tx.commit();
            session.close();
            isDeleted = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public List<Necklace> showNecklace() {
        List<Necklace> tests = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Necklace> cr = cb.createQuery(Necklace.class);
            Root<Necklace> root = cr.from(Necklace.class);
            cr.select(root);
            tests = session.createQuery(cr).getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return tests;
    }

    @Override
    public Necklace findNecklaceById(int id) {
        Necklace necklace = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Necklace> cr = cb.createQuery(Necklace.class);
            Root<Necklace> root = cr.from(Necklace.class);
            cr.select(root).where(cb.equal(root.get("testResultId"), id));
            necklace = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return necklace;
    }
}
