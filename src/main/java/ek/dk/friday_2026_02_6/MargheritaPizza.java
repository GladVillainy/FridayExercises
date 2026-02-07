package ek.dk.friday_2026_02_6;

public class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza $" + getCost();
    }

    @Override
    public double getCost() {
        return 5;
    }
}
