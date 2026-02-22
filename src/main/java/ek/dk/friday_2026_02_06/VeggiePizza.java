package ek.dk.friday_2026_02_06;

public class VeggiePizza implements Pizza {
    @Override
    public String getDescription() {
        return "Veggie Pizza";
    }

    @Override
    public double getCost() {
        return 6;
    }
}
