package mts.ru.predator;

import mts.ru.Animal;

import java.math.BigDecimal;
import java.util.List;

public class Wolf extends Predator {

    private final static String WOLF_BREED = "Волк";

    public Wolf(String name, BigDecimal cost, String character, List<String> incompatibleAnimals) {
        super.breed = WOLF_BREED;
        super.incompatibleAnimals = incompatibleAnimals;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
