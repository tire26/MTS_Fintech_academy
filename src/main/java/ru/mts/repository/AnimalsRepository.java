package ru.mts.repository;


import ru.mts.model.Animal;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 *  Хранилище создаваемых животных
 */
public interface AnimalsRepository {

    /**
     * Находит всех животных в хранилище которые родились в високосный год
     *
     * @return массив из имен животных
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Находит всех животных в хранилище, возраст которых
     * старше N лет, если таких нет, то возвращает самого старшего
     * @param N возраст
     * @return массив животных
     */
    Map<Animal, Integer> findOlderAnimal(int N);

    /**
     * @return возвращает массив животных в хранилище, у которых есть дубликаты
     */
    Map<String, Integer> findDuplicate();

    /**
     * Вызывает внутри метод findDuplicate() и выводит в консоль результат
     */
    void printDuplicate();
}
