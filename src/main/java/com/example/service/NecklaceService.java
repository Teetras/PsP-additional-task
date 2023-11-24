package com.example.service;

import com.example.entity.Gem;
import com.example.entity.Necklace;

import java.util.List;

public interface NecklaceService {
    boolean addNecklace(Necklace necklace);
    boolean updateNecklace(Necklace necklace);
    boolean deleteNecklace(int id);
    List<Necklace> showNecklace();
    Necklace findNecklaceById(int id);
}