package ru.mts.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {

    public final static String CAT_VOICE = "Мяу-мяу";
    public final static String CAT_BREED = "Кот";

    public Cat(String name, BigDecimal cost, String character, LocalDate birthdate) {
        super.breed = CAT_BREED;
        super.voice = CAT_VOICE;
        super.birthDate = birthdate;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
