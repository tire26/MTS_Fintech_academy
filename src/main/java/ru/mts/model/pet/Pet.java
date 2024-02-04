package ru.mts.model.pet;

import ru.mts.model.AbstractAnimal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public abstract class Pet extends AbstractAnimal {
    protected String voice; // какой-то звук домашнего животного

    public Pet(String name, BigDecimal cost, String character, LocalDate birthdate, String breed, String voice) {
        super.birthDate = birthdate;
        super.name = name;
        super.cost = cost;
        super.character = character;
        super.breed = breed;
        this.voice = voice;
    }

    public String getVoice() {
        return voice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        if (!super.equals(o)) return false;

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
