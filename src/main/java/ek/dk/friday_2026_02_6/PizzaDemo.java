package ek.dk.friday_2026_02_6;

public class PizzaDemo {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();

        //Factory pattern
        Pizza veggie = factory.createPizza("Veggie");
        Pizza margherita = factory.createPizza("Margherita");

        //Decorator pattern
        String currency = "$";

        Pizza pizza1 =
                new PepperoniTopping(
                new CheeseTopping(
                        factory.createPizza("Margherita")));

        Pizza pizza2 =
                new CheeseTopping(
                factory.createPizza("Veggie"));

        System.out.println(pizza1.getDescription() +" "+ pizza1.getCost() + currency);
        System.out.println(pizza2.getDescription() +" "+ pizza2.getCost() + currency);


        // TODO: Place an order with a delivery strategy
    }
}
