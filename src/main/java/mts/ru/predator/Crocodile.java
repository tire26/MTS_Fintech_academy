package mts.ru.predator;

import mts.ru.Animal;

import java.math.BigDecimal;
import java.util.List;

public class Crocodile extends Predator {

    private final static String CROCODILE_BREED = "Крокодил";

    public Crocodile(String name, BigDecimal cost, String character, List<String> incompatibleAnimals) {
        super.breed = CROCODILE_BREED;
        super.incompatibleAnimals = incompatibleAnimals;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
