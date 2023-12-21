package mts.ru.predator;

import mts.ru.AbstractAnimal;
import mts.ru.Animal;

import java.util.List;

public abstract class Predator extends AbstractAnimal {
    protected List<String> incompatibleAnimals;

    public boolean isCompatibleWithAnimal(Animal animal) {
        for (String breed : incompatibleAnimals) {
            if (animal.getName().equals(breed)) {
                return false;
            }
        }
        return true;
    }

    public List<String> getIncompatibleAnimals() {
        return incompatibleAnimals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predator)) return false;

        Predator predator = (Predator) o;
        return super.equals(o) && getIncompatibleAnimals().equals(predator.getIncompatibleAnimals());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + getIncompatibleAnimals().hashCode();
    }

    @Override
    public String toString() {
        return "Хищник {" +
                ", порода='" + breed + '\'' +
                ", имя='" + name + '\'' +
                ", цена=" + cost +
                ", характер='" + character + '\'' +
                '}';
    }
}
