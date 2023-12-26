package ru.mts.pet;

import ru.mts.AbstractAnimal;

public abstract class Pet extends AbstractAnimal {
    protected String voice; // какой-то звук домашнего животного

    public String getVoice() {
        return voice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        if (!super.equals(o)) return false;

        Pet pet = (Pet) o;

        return getVoice().equals(pet.getVoice());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getVoice().hashCode();
        return result;
    }
}
