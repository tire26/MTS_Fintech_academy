package ru.mts.animalsconstructstarter.factory;

import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.animalsconstructstarter.model.AnimalType;
import ru.mts.animalsconstructstarter.model.FactoryType;

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
