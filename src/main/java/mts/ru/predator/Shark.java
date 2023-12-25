package mts.ru.predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Shark extends Predator {

    private final static String SHARK_BREED = "Акула";

    public Shark(String name, BigDecimal cost, String character, List<String> incompatibleAnimals, LocalDate birthdate) {
        super.breed = SHARK_BREED;
        super.birthDate = birthdate;
        super.incompatibleAnimals = incompatibleAnimals;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
