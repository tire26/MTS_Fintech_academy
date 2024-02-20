package ru.mts.factory;

import ru.mts.model.Animal;
import ru.mts.model.AnimalType;
import ru.mts.model.FactoryType;

public interface AnimalFactory {

    /**
     * метод создаёт животное с уникальными параметрами
     * @return возвращает созданного животного
     */
    Animal createAnimal();

    /**
     * метод устанавливает тип животного для фабрикик
     * @param animalType тип животного
     */
    void setAnimalType(AnimalType animalType);

    FactoryType getFactoryType();
}
