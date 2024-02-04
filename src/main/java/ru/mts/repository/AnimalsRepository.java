package ru.mts.repository;

import ru.mts.model.Animal;

/**
 *  Хранилище создаваемых животных
 */
public interface AnimalsRepository {

    /**
     * находит всех животных в хранилище которые родились в високосный год
     *
     * @return массив из имен животных
     */
    String[] findLeapYearNames();

    /**
     * находит всех животных в хранилище, возраст которых
     * старше N лет
     * @param N возраст
     * @return массив животных
     */
    Animal[] findOlderAnimal(int N);


    /**
     * @return вовзращает массив животных в хранилище, у которых есть дупликаты
     */
    Animal[] findDuplicate();
}
