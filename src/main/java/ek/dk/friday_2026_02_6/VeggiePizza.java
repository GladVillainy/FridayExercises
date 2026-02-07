package ek.dk.friday_2026_02_6;

public class VeggiePizza implements Pizza {
    @Override
    public String getDescription() {
        return "Veggie Pizza $" + getCost();
    }

    @Override
    public double getCost() {
        return 6;
    }
}
