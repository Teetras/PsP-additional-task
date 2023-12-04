package com.example.demo1.MenuFunctions;

import com.example.demo1.entity.Gem;
import com.example.demo1.entity.Necklace;
import com.example.demo1.entity.NecklaceGem;
import com.example.demo1.entity.NecklaceGemId;
import com.example.demo1.service.GemService;
import com.example.demo1.service.serviceImpl.GemServiceImpl;

import java.util.*;

public class NecklaceCreator {
    GemService gemService=  new GemServiceImpl();

    public List<Necklace> createNecklacesByWeight(double targetWeight, int numberOfNecklaces) {//подбираем камни для создания ожерелья по весу
        List<Necklace> necklaces = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfNecklaces; i++) {
            Necklace necklace = new Necklace();
            List<Gem> gems = new ArrayList<>();
            double currentWeight = 0.0;

            while (currentWeight < targetWeight) {
                List<Gem> availableGems = gemService.showGem();
                int randomGemIndex = random.nextInt(availableGems.size());
                Gem randomGem = availableGems.get(randomGemIndex);

                // Проверяем, чтобы добавление камня не превысило целевой вес
                if (currentWeight + randomGem.getWeight() <= targetWeight) {
                    System.out.println(currentWeight);
                    // Добавляем камень в ожерелье
                    gems.add(randomGem);

                    // Обновляем текущий вес ожерелья
                    currentWeight += randomGem.getWeight();
                }
            }

            // Убеждаемся, что текущий вес ожерелья точно равен целевому весу
            if (currentWeight > targetWeight) {
                gems.remove(gems.size() - 1);
            }

            // Устанавливаем остальные свойства ожерелья (например, имя, общая стоимость и т. д.)
            necklace.setName("Necklace " + (i + 1));
            necklace.setTotalPrice(calculateTotalPrice(gems));
            necklace.setWeight(currentWeight);



            Map<String, Integer> gemCounts = gemCounts(gems);
            List<NecklaceGem> necklaceGems = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : gemCounts.entrySet()) {
                String gemName = entry.getKey();
                int quantity = entry.getValue();

                // Найдите объект Gem по названию камня (gemName)
                Gem gem = gemService.findGemByName(gemName); // Замените findGemByName на соответствующий метод для поиска Gem по названию

                if (gem != null) {
                    NecklaceGem necklaceGem = new NecklaceGem();
                    NecklaceGemId necklaceGemId = new NecklaceGemId(necklace.getNecklaceId(), gem.getGemId());

                    necklaceGem.setId(necklaceGemId);
                    necklaceGem.setNecklace(necklace);
                    necklaceGem.setGem(gem);
                    necklaceGem.setQuantity(quantity);

                    necklaceGems.add(necklaceGem);
                }
            }

            necklace.setNecklaceGems(necklaceGems);
            necklaces.add(necklace);
        }

        return necklaces;
    }

    public String printGemCounts(List<NecklaceGem> necklaces) {
        Map<String, Integer> gemCounts = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (NecklaceGem necklaceGem : necklaces) {
            String gemName = necklaceGem.getGem().getName();
            Integer getCount = necklaceGem.getQuantity();
            result.append(gemName).append(": ").append(getCount).append("\n");
        }


        return result.toString();
    }

    public Map<String, Integer> gemCounts(List<Gem> gems) {//считаем количество дубликатов камней в ожерелье
        Map<String, Integer> gemCounts = new HashMap<>();

        for (Gem gem : gems) {
            String gemName = gem.getName(); // Получаем название камня

            // Проверяем, есть ли уже такое название камня в карте
            if (gemCounts.containsKey(gemName)) {
                // Увеличиваем счетчик количества камня
                int count = gemCounts.get(gemName);
                gemCounts.put(gemName, count + 1);
            } else {
                // Добавляем новое название камня в карту
                gemCounts.put(gemName, 1);
            }
        }

        return gemCounts;
    }

    private double calculateTotalPrice(List<Gem> gems) {//считаем стоимость ожелье
        double totalPrice = 0.0;

        for (Gem gem : gems) {
            totalPrice += gem.getPrice();
        }

        return totalPrice;
    }
}