package mts.ru.pet;

import java.math.BigDecimal;

public class Cat extends Pet {

    public final static String CAT_VOICE = "Мяу-мяу";
    public final static String CAT_BREED = "Кот";

    public Cat(String name, BigDecimal cost, String character) {
        super.breed = CAT_BREED;
        super.voice = CAT_VOICE;

        super.name = name;
        super.cost = cost;
        super.character = character;
    }
}
