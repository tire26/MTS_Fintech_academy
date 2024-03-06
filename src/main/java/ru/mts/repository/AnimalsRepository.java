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

    /**
     * Находит средний возраст всех животных и выводит его в консоль
     * @param animals список животных
     */
    Double findAverageAge(List<Animal> animals);

    /**
     * Ищет животных, возраст которых больше 5 лет и стоимость больше средней стоимости
     * @param animals список животных
     * @return отсортированный по дате рождений по возрастанию список
     */
    List<Animal> findOldAndExpensive(List<Animal> animals);

    /**
     * Ищет 3 животных с самой низкой ценой
     * @param animals список животных
     * @return список имён животных
     */
    List<String> findMinConstAnimals(List<Animal> animals);
}
