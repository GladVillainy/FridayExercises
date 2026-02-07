package ek.dk.friday_2026_02_6;

public class PizzaDemo {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();

        factory.createPizza("Veggie");
        factory.createPizza("Margherita");

        // TODO: Create a pizza using the factory
        // TODO: Add some toppings (decorators)
        // TODO: Place an order with a delivery strategy
    }
}
