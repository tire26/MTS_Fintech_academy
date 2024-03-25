package ru.mts.model.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Horse extends Pet {
    public final static String HORSE_VOICE = "Иго-го";
    public final static String HORSE_BREED = "Лошадь";
    public Horse(String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(name, cost, character, birthDate, HORSE_BREED, HORSE_VOICE);
    }
}
