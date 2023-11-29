package com.example.demo1.dao;

import com.example.demo1.entity.Necklace;

import java.util.List;

public interface NecklaceDao {
    boolean addNecklace(Necklace necklace);
    boolean updateNecklace(Necklace necklace);
    boolean deleteNecklace(int id);
    List<Necklace> showNecklace();
    Necklace findNecklaceById(int id);
}
