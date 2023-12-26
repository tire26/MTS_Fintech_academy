package ru.mts.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {

    public final static String DOG_VOICE = "Гав-гав";
    public final static String DOG_BREED = "Собака";

    public Dog(String name, BigDecimal cost, String character, LocalDate birthdate) {
        super.breed = DOG_BREED;
        super.voice = DOG_VOICE;
        super.birthDate = birthdate;
        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
