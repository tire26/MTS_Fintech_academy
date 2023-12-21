package mts.ru;

import java.math.BigDecimal;

public abstract class AbstractAnimal implements Animal{

    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // характер

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAnimal)) return false;

        AbstractAnimal that = (AbstractAnimal) o;

        if (!getBreed().equals(that.getBreed())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getCost().equals(that.getCost())) return false;
        return getCharacter().equals(that.getCharacter());
    }

    @Override
    public int hashCode() {
        int result = getBreed().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCost().hashCode();
        result = 31 * result + getCharacter().hashCode();
        return result;
    }
}
