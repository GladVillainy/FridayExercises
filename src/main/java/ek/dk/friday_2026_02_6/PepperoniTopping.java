package ek.dk.friday_2026_02_6;

public class PepperoniTopping extends ToppingDecorator {
    public PepperoniTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        int toppingPrice = 1;
        return pizza.getCost()+toppingPrice;
    }
}
