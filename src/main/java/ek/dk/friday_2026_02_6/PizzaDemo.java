package ek.dk.friday_2026_02_6;

public class PizzaDemo {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();

        //Factory pattern
        Pizza veggie = factory.createPizza("Veggie");
        Pizza margherita = factory.createPizza("Margherita");

        //Decorator pattern

        Pizza pizza1 =
                new PepperoniTopping(
                new CheeseTopping(
                        factory.createPizza("Margherita")));

        Pizza pizza2 =
                new CheeseTopping(
                factory.createPizza("Veggie"));

        Pizza pizza3 =
                new CheeseTopping(
                        factory.createPizza("Margherita"));




        //Strategy pattern
        Order order1 = new Order(pizza1);
        order1.setDeliveryStrategy(new DroneDelivery());
        order1.processOrder();

        Order order2 = new Order(pizza2);
        order2.setDeliveryStrategy(new HomeDelivery());
        order2.processOrder();

        Order order3 = new Order(pizza3);
        order3.setDeliveryStrategy(new Pickup());
        order3.processOrder();

    }
}
