package ru.mts.animalsconstructstarter.model.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {

    public final static String CAT_VOICE = "Мяу-мяу";
    public final static String CAT_BREED = "Кот";

    public Cat(String name, BigDecimal cost, String character, LocalDate birthdate) {
        super(name, cost, character, birthdate, CAT_BREED, CAT_VOICE);
    }
}
