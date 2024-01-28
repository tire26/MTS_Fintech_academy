package ru.mts;

public interface SearchService {

    /**
     * находит всех животных которые родились в високосный год
     *
     * @param animals массив животных
     * @return массив из имен животных
     */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * находит всех животных, возраст которых
     * старше N лет
     *
     * @param animals массив животных
     * @param N       возраст
     * @return массив животных
     */
    Animal[] findOlderAnimal(Animal[] animals, int N);


    /**
     * @param animals массив животных
     * @return вовзращает массив животных, у которых есть дупликаты
     */
    Animal[] findDuplicate(Animal[] animals);
}
