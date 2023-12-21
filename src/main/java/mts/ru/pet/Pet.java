package mts.ru.pet;

import mts.ru.AbstractAnimal;

public abstract class Pet extends AbstractAnimal {
    protected String voice; // какой-то звук домашнего животного

    public String getVoice() {
        return voice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;

        Pet pet = (Pet) o;
        return super.equals(o) && getVoice().equals(pet.getVoice());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + getVoice().hashCode();
    }

    @Override
    public String toString() {
        return "Домашнее животное {" +
                "голос='" + voice + '\'' +
                ", порода='" + breed + '\'' +
                ", имя='" + name + '\'' +
                ", цена=" + cost +
                ", характер='" + character + '\'' +
                '}';
    }
}
