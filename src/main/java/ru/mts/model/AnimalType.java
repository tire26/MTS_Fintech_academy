package ru.mts.model;

/**
 * Перечисление типов животных, с возможностью получить конкретную фабрику этого животного
 */
public enum AnimalType {
    CAT {
        @Override
        public FactoryType getFactoryType() {
            return FactoryType.PET_FACTORY;
        }
    },
    CROCODILE {
        @Override
        public FactoryType getFactoryType() {
            return FactoryType.PREDATOR_FACTORY;
        }
    },
    DOG {
        @Override
        public FactoryType getFactoryType() {
            return FactoryType.PET_FACTORY;
        }
    },
    PARROT {
        @Override
        public FactoryType getFactoryType() {
            return FactoryType.PET_FACTORY;
        }
    },
    SHARK {
        @Override
        public FactoryType getFactoryType() {
            return FactoryType.PREDATOR_FACTORY;
        }
    },
    WOLF {
        @Override
        public FactoryType getFactoryType() {
            return FactoryType.PREDATOR_FACTORY;
        }
    };

    /**
     * @return фабрику, соответсвующую типу животного
     */
    public abstract FactoryType getFactoryType();

}
