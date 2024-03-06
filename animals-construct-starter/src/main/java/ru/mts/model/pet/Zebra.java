package ru.mts.model.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Zebra extends Pet{

    public final static String ZEBRA_VOICE = "Иго-го";
    public final static String ZEBRA_BREED = "Зебра";
    public Zebra(String name, BigDecimal cost, String character, LocalDate birthdate) {
        super(name, cost, character, birthdate, ZEBRA_BREED, ZEBRA_VOICE);
    }
}
