package mts.ru.pet;

import java.math.BigDecimal;

public class Parrot extends Pet {

    public final static String PARROT_VOICE = "Кар-кар";
    public final static String PARROT_BREED = "Попугай";

    public Parrot(String name, BigDecimal cost, String character) {
        super.breed = PARROT_BREED;
        super.voice = PARROT_VOICE;

        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
