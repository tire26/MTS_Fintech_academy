package mts.ru;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        System.out.println("Генерация животных методом из CreateAnimalService");
        createAnimalService.createUniqueAnimals();

        System.out.println("Генерация животных методом из CreateAnimalServiceImpl");
        CreateAnimalServiceImpl createAnimalServiceImpl = (CreateAnimalServiceImpl) createAnimalService;
        createAnimalServiceImpl.createUniqueAnimals();

        System.out.println("Генерация n животных методом из CreateAnimalServiceImpl");
        createAnimalServiceImpl.createUniqueAnimals(2);
    }
}