package ru.mts.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Parrot extends Pet {

    public final static String PARROT_VOICE = "Кар-кар";
    public final static String PARROT_BREED = "Попугай";

    public Parrot(String name, BigDecimal cost, String character, LocalDate birthdate) {
        super(name, cost, character, birthdate, PARROT_BREED, PARROT_VOICE);
    }
}
