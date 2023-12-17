package mts.ru.predator;

import mts.ru.Animal;

import java.math.BigDecimal;
import java.util.List;

public class Shark extends Predator {

    private final static String SHARK_BREED = "Акула";

    public Shark(String name, BigDecimal cost, String character, List<String> incompatibleAnimals) {
        super.breed = SHARK_BREED;
        super.incompatibleAnimals = incompatibleAnimals;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
