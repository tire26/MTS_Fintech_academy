package mts.ru.pet;

import mts.ru.AbstractAnimal;

public abstract class Pet extends AbstractAnimal {
    protected String voice; // какой-то звук домашнего животного

    public String getVoice() {
        return voice;
    }
}
