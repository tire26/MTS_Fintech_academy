package mts.ru;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class AbstractAnimal implements Animal{

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
    public boolean equals(Object o) {
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
