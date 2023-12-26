package ru.mts.pet;

import ru.mts.AbstractAnimal;

import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new StringJoiner(", ", Pet.class.getSimpleName() + "[", "]")
                .add("breed='" + breed + "'")
                .add("name='" + name + "'")
                .add("cost=" + cost)
                .add("character='" + character + "'")
                .add("birthDate=" + formatter.format(birthDate))
                .toString();
    }
}
