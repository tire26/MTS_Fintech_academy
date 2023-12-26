package ru.mts;

public interface AnimalFactory {

    /**
     * метод создаёт животное с уникальными параметрами
     * @return возвращает созданного животного
     */
    Animal createAnimal();
}
