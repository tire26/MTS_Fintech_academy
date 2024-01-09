package ru.mts;

public interface SearchService {

    /**
     * находит всех животных которые родились в високосный год
     * @param animals массив животных
     * @return массив из имен животных
     */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * находит всех животных, возраст которых
     * старше N лет
     * @param animals массив животных
     * @param N возраст
     * @return массив животных
     */
    Animal[] findOlderAnimal(Animal[] animals, int N);

    /**
     * выводит на экран дубликаты животных
     * @param animals массив животных
     */
    void findDuplicate(Animal[] animals);
}
