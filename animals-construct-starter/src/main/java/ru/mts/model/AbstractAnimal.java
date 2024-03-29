package ru.mts.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractAnimal implements Animal, Comparable<Animal> {

    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // дата рождения

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public int compareTo(Animal o) {
        int breedComparison = this.breed.compareTo(o.getBreed());
        if (breedComparison != 0) {
            return breedComparison;
        }

        int nameComparison = this.name.compareTo(o.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        int costComparison = this.cost.compareTo(o.getCost());
        if (costComparison != 0) {
            return costComparison;
        }

        int characterComparison = this.character.compareTo(o.getCharacter());
        if (characterComparison != 0) {
            return characterComparison;
        }

        return this.birthDate.compareTo(o.getBirthDate());
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof AbstractAnimal)) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        if (!getBreed().equals(that.getBreed())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getCost().equals(that.getCost())) return false;
        if (!getCharacter().equals(that.getCharacter())) return false;
        return getBirthDate().equals(that.getBirthDate());
    }

    @Override
    public int hashCode() {
        int result = getBreed().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCost().hashCode();
        result = 31 * result + getCharacter().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        return result;
    }
}
