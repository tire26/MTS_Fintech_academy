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
            return new PetFactory();
        }
    },
    CROCODILE {
        @Override
        public AnimalFactory getFactory() {
            return new PredatorFactory();
        }
    },
    DOG {
        @Override
        public AnimalFactory getFactory() {
            return new PetFactory();
        }
    },
    PARROT {
        @Override
        public AnimalFactory getFactory() {
            return new PetFactory();
        }
    },
    SHARK {
        @Override
        public AnimalFactory getFactory() {
            return new PredatorFactory();
        }
    },
    WOLF {
        @Override
        public AnimalFactory getFactory() {
            return new PredatorFactory();
        }
    };

    /**
     * @return фабрику, соответсвующую типу животного
     */
    public abstract AnimalFactory getFactory();
}
