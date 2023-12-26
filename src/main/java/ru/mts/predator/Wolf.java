package ru.mts.predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Wolf extends Predator {

    private final static String WOLF_BREED = "Волк";

    public Wolf(String name, BigDecimal cost, String character, List<String> incompatibleAnimals, LocalDate birthdate) {
        super.breed = WOLF_BREED;
        super.birthDate = birthdate;
        super.incompatibleAnimals = incompatibleAnimals;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
