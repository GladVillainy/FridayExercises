package ek.dk.friday_2026_02_06;

public abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;
    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}
