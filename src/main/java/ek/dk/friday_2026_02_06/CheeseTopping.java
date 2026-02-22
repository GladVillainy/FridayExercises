package ek.dk.friday_2026_02_06;

public class CheeseTopping extends ToppingDecorator {
    public CheeseTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        int toppingPrice = 1;
        return pizza.getCost()+toppingPrice;
    }
}
