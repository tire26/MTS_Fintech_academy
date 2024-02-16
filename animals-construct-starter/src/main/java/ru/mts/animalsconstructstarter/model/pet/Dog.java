package ru.mts.animalsconstructstarter.model.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {

    public final static String DOG_VOICE = "Гав-гав";
    public final static String DOG_BREED = "Собака";

    public Dog(String name, BigDecimal cost, String character, LocalDate birthdate) {
        super(name, cost, character, birthdate, DOG_BREED, DOG_VOICE);
    }
}
