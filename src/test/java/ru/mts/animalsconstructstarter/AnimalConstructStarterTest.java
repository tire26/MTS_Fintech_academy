package ru.mts.animalsconstructstarter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.mts.animalsconstructstarter.config.AnimalFactoryTestConfig;
import ru.mts.animalsconstructstarter.model.Animal;
import ru.mts.animalsconstructstarter.model.AnimalType;
import ru.mts.animalsconstructstarter.model.FactoryType;
import ru.mts.animalsconstructstarter.model.pet.Cat;
import ru.mts.animalsconstructstarter.model.predator.Wolf;
import ru.mts.animalsconstructstarter.service.AnimalFactoryService;
import ru.mts.animalsconstructstarter.service.CreateAnimalService;

@SpringBootTest(
        classes = {AnimalFactoryTestConfig.class}
)
@DisplayName("Тестирование классов стартера animals-construct-starter")
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
public class AnimalConstructStarterTest {

    private CreateAnimalService createAnimalService;
    private AnimalFactoryService animalFactoryService;

    @Test
    @DisplayName("Проверка корректной установки фабрики для указанного типа животного")
    public void testAnimalCreationFactoryInjection() {
        createAnimalService.setAnimalType(AnimalType.WOLF);
        Assertions.assertEquals(createAnimalService.getAnimalFactory().getFactoryType(), FactoryType.PREDATOR_FACTORY);
    }

    @Test
    @DisplayName("Проверка создания разных фабрик")
    public void testDiffFactories() {
        createAnimalService.setAnimalType(AnimalType.CAT);
        FactoryType factoryType = createAnimalService.getAnimalFactory().getFactoryType();
        Assertions.assertEquals(FactoryType.PET_FACTORY, factoryType);

        createAnimalService.setAnimalType(AnimalType.WOLF);
        FactoryType factoryType1 = createAnimalService.getAnimalFactory().getFactoryType();
        Assertions.assertEquals(FactoryType.PREDATOR_FACTORY, factoryType1);
    }

    @Test
    @DisplayName("Проверка создания разных животных")
    public void testDiffAnimalsCreation() {
        createAnimalService.setAnimalType(AnimalType.CAT);
        Animal animal = createAnimalService.getAnimalFactory().createAnimal();
        Assertions.assertInstanceOf(Cat.class, animal);

        createAnimalService.setAnimalType(AnimalType.WOLF);
        Animal animal1 = createAnimalService.getAnimalFactory().createAnimal();
        Assertions.assertInstanceOf(Wolf.class, animal1);
    }

    @Test
    @DisplayName("Проверка количества создаваемых живоных методом createUniqueAnimals()")
    public void testAnimalCreationCount() {
        Animal[] uniqueAnimals = createAnimalService.createUniqueAnimals();
        Assertions.assertEquals(uniqueAnimals.length, 10);
    }

    @Test
    @DisplayName("Проверка установки null тип животного")
    public void testNullSetAnimalType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalService.setAnimalType(null));
    }

    @Test
    @DisplayName("Проверка null метода getFactoryBy(null)")
    public void testNullGetFactoryBy() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> animalFactoryService.getFactoryBy(null));
    }

    @Autowired
    public void setCreateAnimalService(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }
    @Autowired
    public void setAnimalFactoryService(AnimalFactoryService animalFactoryService) {
        this.animalFactoryService = animalFactoryService;
    }
}
