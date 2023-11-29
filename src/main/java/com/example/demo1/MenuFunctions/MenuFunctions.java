package com.example.demo1.MenuFunctions;

import com.example.demo1.entity.Gem;
import com.example.demo1.entity.Necklace;
import com.example.demo1.entity.NecklaceGem;
import com.example.demo1.entity.User;
import com.example.demo1.service.GemService;
import com.example.demo1.service.NecklaceService;
import com.example.demo1.service.UserService;
import com.example.demo1.service.serviceImpl.GemServiceImpl;
import com.example.demo1.service.serviceImpl.NacklaceServiceImpl;
import com.example.demo1.service.serviceImpl.UserServiceImpl;
import com.example.demo1.sessionFactory.SessionFactoryImpl;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
public class MenuFunctions {
    Scanner in = new Scanner(System.in);
    GemService gemService = new GemServiceImpl();
    NecklaceService necklaceService = new NacklaceServiceImpl();

    UserService userService = new UserServiceImpl();


    public boolean addNecklace(Necklace necklace) {
        boolean isAdded = false;
        try {
            if (necklaceService.addNecklace(necklace))
                isAdded = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }


    public User findUserById(int id) {
        User user = null;
        try {
            user = userService.findUserById(id);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User findUserByLogin(String login) {
        User user = null;
        try {
            user = userService.findUserByLogin(login);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return user;
    }



    public boolean addGem(String name,double transparency,double value ,double weight) {
        boolean isAdded = false;
        try {
            Gem gem = new Gem(name, value,
                    transparency, weight);
            gemService.addGem(gem);
            isAdded = true;
        }
        catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }


    public List<Gem> showDiapasonOpacityGem(double startOpacity, double endOpacity) {
        List<Gem> gems = null;
        try {
            gems = gemService.showDiapasonOpacityGem(startOpacity, endOpacity);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }

    public List<Gem> showSortedByWeightGem() {
        List<Gem> gems = null;
        try {
            gems = gemService.showSortedByWeightGem();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }

    public List<Gem> showSortedByPriceGem() {
        List<Gem> gems = null;
        try {
            gems = gemService.showSortedByPriceGem();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }




    public List<Gem> showGem() {
        List<Gem> gems = null;
        try {
            gems = gemService.showGem();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }




    public boolean addNecklaceGem(NecklaceGem necklaceGem) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(necklaceGem);
            tx.commit();
            session.close();
            isAdded = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }
}
