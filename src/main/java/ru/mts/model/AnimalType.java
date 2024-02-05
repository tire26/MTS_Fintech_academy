package ru.mts.model;

import ru.mts.utils.AnimalFactory;
import ru.mts.utils.PetFactory;
import ru.mts.utils.PredatorFactory;

/**
 * Перечисление типов животных, с возможностью получить конкретную фабрику этого животного
 */
public enum AnimalType {
    CAT {
        @Override
        public AnimalFactory getFactory() {
            return petFactory;
        }
    },
    CROCODILE {
        @Override
        public AnimalFactory getFactory() {
            return predatorFactory;
        }
    },
    DOG {
        @Override
        public AnimalFactory getFactory() {
            return petFactory;
        }
    },
    PARROT {
        @Override
        public AnimalFactory getFactory() {
            return petFactory;
        }
    },
    SHARK {
        @Override
        public AnimalFactory getFactory() {
            return predatorFactory;
        }
    },
    WOLF {
        @Override
        public AnimalFactory getFactory() {
            return predatorFactory;
        }
    };

    /**
     * @return фабрику, соответсвующую типу животного
     */
    public abstract AnimalFactory getFactory();

    private final static PetFactory petFactory = new PetFactory();
    private final static PredatorFactory predatorFactory = new PredatorFactory();
}
