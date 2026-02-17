package ek.dk.friday_2026_02_6;

import java.util.Scanner;

public class PizzaDemo {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();

        boolean run = true;
        Scanner scan = new Scanner(System.in);

        while (run) {
            //Basic pizza (Factory)
            System.out.println("Which pizza would you like to order? " +
                    "Margherita or veggie?");
            String pizzaAnswer = scan.next();
            Pizza pizza = factory.createPizza(pizzaAnswer);
            if (pizza == null) {
                System.out.println("We don't have that type of pizza");
                continue;
            }


            //Topping (wrapped)
            System.out.println("Do you want any topping? " +
                    "We have cheese and pepperoni." +
                    " Type n for no topping or type exit");

            String toppingAnser = scan.next();

            if (toppingAnser.equalsIgnoreCase("n")) {}
            else if (toppingAnser.equalsIgnoreCase("exit")) {
                break;
            }
            else if (toppingAnser.equalsIgnoreCase("Cheese")) {
                pizza = new CheeseTopping(pizza);
            }
            else if (toppingAnser.equalsIgnoreCase("pepperoni")) {
                pizza = new PepperoniTopping(pizza);
            }
            else {
                System.out.println("We don't have that");
                continue;
            }


            //Delivery
            System.out.println("How should the pizza be delivered?" +
                    " Takeaway, delivered home or by drone?");

            String deliveryAnswer = scan.next();

            if (deliveryAnswer.equalsIgnoreCase("exit")) {
                return;
            }
            else if (deliveryAnswer.equalsIgnoreCase("Takeaway")) {
                Order order = new Order(pizza);
                order.setDeliveryStrategy(new Pickup());
                order.processOrder();
            }
            else if (deliveryAnswer.equalsIgnoreCase("Home")) {
                Order order = new Order(pizza);
                order.setDeliveryStrategy(new HomeDelivery());
                order.processOrder();
            }
            else if (deliveryAnswer.equalsIgnoreCase("Drone")) {
                Order order = new Order(pizza);
                order.setDeliveryStrategy(new DroneDelivery());
                order.processOrder();
            }
            else {
                System.out.println("We don't have that");
                return;
            }


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
            // order1.processOrder();

            Order order2 = new Order(pizza2);
            order2.setDeliveryStrategy(new HomeDelivery());
            // order2.processOrder();

            Order order3 = new Order(pizza3);
            order3.setDeliveryStrategy(new Pickup());
            // order3.processOrder();


        }
    }
}
