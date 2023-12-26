package ru.mts.predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Crocodile extends Predator {

    private final static String CROCODILE_BREED = "Крокодил";

    public Crocodile(String name, BigDecimal cost, String character, List<String> incompatibleAnimals, LocalDate birthdate) {
        super.breed = CROCODILE_BREED;
        super.incompatibleAnimals = incompatibleAnimals;
        super.birthDate = birthdate;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
