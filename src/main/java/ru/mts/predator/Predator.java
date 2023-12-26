package ru.mts.predator;

import ru.mts.AbstractAnimal;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringJoiner;

public abstract class Predator extends AbstractAnimal {
    protected List<String> incompatibleAnimals;

    public List<String> getIncompatibleAnimals() {
        return incompatibleAnimals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predator)) return false;
        if (!super.equals(o)) return false;

        Predator predator = (Predator) o;

        return getIncompatibleAnimals().equals(predator.getIncompatibleAnimals());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIncompatibleAnimals().hashCode();
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new StringJoiner(", ", Predator.class.getSimpleName() + "[", "]")
                .add("breed='" + breed + "'")
                .add("name='" + name + "'")
                .add("cost=" + cost)
                .add("character='" + character + "'")
                .add("birthDate=" + formatter.format(birthDate))
                .toString();
    }
}
