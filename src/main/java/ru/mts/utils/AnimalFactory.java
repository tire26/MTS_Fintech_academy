package ru.mts.utils;

import ru.mts.model.Animal;
import ru.mts.model.AnimalType;

public interface AnimalFactory {

    /**
     * метод создаёт животное с уникальными параметрами
     * @return возвращает созданного животного
     */
    Animal createAnimal();

    void setAnimalType(AnimalType animalType);
}
