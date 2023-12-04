package com.example.demo1.service;
import com.example.demo1.entity.Gem;

import java.util.List;

public interface GemService {//интерфейс содержащий необходимые функции для проведения операций над объектом камня в бд
    boolean addGem(Gem gem);
    boolean updateGem(Gem gem);
    boolean deleteGem(int id);
    List<Gem> showGem();
    List<Gem> showSortedByPriceGem();
    List<Gem> showSortedByWeightGem();
    List<Gem> showDiapasonOpacityGem(double startOpacity, double endOpacity);
    Gem findGemById(int id);
    Gem findGemByName(String name);
}