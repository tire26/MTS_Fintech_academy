package ru.mts.model.predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Wolf extends Predator {

    private final static String WOLF_BREED = "Волк";

    public Wolf(String name, BigDecimal cost, String character,List<String> incompatibleAnimals,  LocalDate birthdate) {
        super(name, cost, character, birthdate, WOLF_BREED, incompatibleAnimals);
    }
}
