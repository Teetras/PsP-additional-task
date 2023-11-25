package com.example.service.serviceImpl;


import com.example.dao.GemDao;
import com.example.dao.daoImpl.GemDaoImpl;
import com.example.entity.Gem;
import com.example.service.GemService;
import lombok.NoArgsConstructor;

import org.hibernate.HibernateError;

import java.util.List;

@NoArgsConstructor
public class GemServiceImpl implements GemService {
    GemDao gemDao = new GemDaoImpl();
    @Override
    public boolean addGem(Gem gem) {
        boolean isAdded = false;
        try {
            gemDao.addGem(gem);
            isAdded = true;
        }
        catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateGem(Gem gem) {
        boolean isUpdated = false;
        try {
            if(gemDao.updateGem(gem)) {
                isUpdated = true;
            }
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteGem(int id) {
        boolean isDeleted = false;
        try {
            if(gemDao.deleteGem(id)) {
                isDeleted = true;
            }
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Gem> showGem() {
        List<Gem> gems = null;
        try {
            gems = gemDao.showGem();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }

    @Override
    public List<Gem> showSortedByPriceGem() {
        List<Gem> gems = null;
        try {
            gems = gemDao.showSortedByPriceGem();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }

    @Override
    public List<Gem> showSortedByWeightGem() {
        List<Gem> gems = null;
        try {
            gems = gemDao.showSortedByWeightGem();
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }

    @Override
    public List<Gem> showDiapasonOpacityGem(double startOpacity, double endOpacity) {
        List<Gem> gems = null;
        try {
            gems = gemDao.showDiapasonOpacityGem(startOpacity, endOpacity);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gems;
    }



    @Override
    public Gem findGemById(int id) {
        Gem gem = null;
        try {
            gem = gemDao.findGemById(id);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gem;
    }

    @Override
    public Gem findGemByName(String name) {
        Gem gem = null;
        try {
            gem = gemDao.findGemByName(name);
        } catch (HibernateError e) {
            throw new RuntimeException(e);
        }
        return gem;
    }
}

