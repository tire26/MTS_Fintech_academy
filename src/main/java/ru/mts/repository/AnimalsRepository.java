package ru.mts.repository;


import ru.mts.model.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    Map<String, List<Animal>> findDuplicate();

    /**
     * Вызывает внутри метод findDuplicate() и выводит в консоль результат
     */
    void printDuplicate();
}
