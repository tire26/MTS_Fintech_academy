package ru.mts.predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Shark extends Predator {

    private final static String SHARK_BREED = "Акула";

    public Shark(String name, BigDecimal cost, String character,List<String> incompatibleAnimals,  LocalDate birthdate) {
        super(name, cost, character, birthdate, SHARK_BREED, incompatibleAnimals);
    }
}
