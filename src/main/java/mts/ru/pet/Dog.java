package mts.ru.pet;

import java.math.BigDecimal;

public class Dog extends Pet {

    public final static String DOG_VOICE = "Гав-гав";
    public final static String DOG_BREED = "Собака";

    public Dog(String name, BigDecimal cost, String character) {
        super.breed = DOG_BREED;
        super.voice = DOG_VOICE;

        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
