package mts.ru;

import java.util.List;

public interface SearchService {

    /**
     * находит всех животных которые родились в високосный год
     * @param animals массив животных
     * @return массив из имен животных
     */
    List<String> findLeapYearNames(List<Animal> animals);

    /**
     * находит всех животных, возраст которых
     * старше N лет
     * @param animals массив животных
     * @param N возраст
     * @return массив животных
     */
    List<Animal> findOlderAnimal(List<Animal> animals, int N);

    /**
     * выводит на экран дубликаты животных
     * @param animals массив животных
     */
    void findDuplicate(List<Animal> animals);
}
