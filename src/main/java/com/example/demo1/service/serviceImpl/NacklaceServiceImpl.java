package com.example.demo1.service.serviceImpl;


import com.example.demo1.dao.NecklaceDao;
import com.example.demo1.dao.daoImpl.NecklaceDaoImpl;
import com.example.demo1.entity.Necklace;
import com.example.demo1.service.NecklaceService;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateError;

import java.util.List;

@NoArgsConstructor
public class NacklaceServiceImpl implements NecklaceService {// реализация функций интерфейса
    NecklaceDao necklaceDao = new NecklaceDaoImpl();

    @Override
    public boolean addNecklace(Necklace necklace) {
        boolean isAdded = false;
        try {
            if (necklaceDao.addNecklace(necklace))
                isAdded = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateNecklace(Necklace necklace) {
        boolean isUpdated = false;
        try {
            if (necklaceDao.updateNecklace(necklace))
                isUpdated = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteNecklace(int id) {
        boolean isDeleted = false;
        try {
            if (necklaceDao.deleteNecklace(id))
                isDeleted = true;
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Necklace> showNecklace() {
        List<Necklace> necklace = null;
        try {
            necklace = necklaceDao.showNecklace();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return necklace;
    }

    @Override
    public Necklace findNecklaceById(int id) {
        Necklace necklace = null;
        try {
            necklace = necklaceDao.findNecklaceById(id);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return necklace;
    }



}
