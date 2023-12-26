package ru.mts.predator;

import ru.mts.AbstractAnimal;

import java.util.List;

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
}
