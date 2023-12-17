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
}
