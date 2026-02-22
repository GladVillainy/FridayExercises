package ek.dk.friday_2026_02_06;

public class Order {
    private DeliveryStrategy deliveryStrategy;
    private Pizza pizza;

    public Order(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    public void processOrder() {
        String currency = "$";
        System.out.println("\nOrder: " + pizza.getDescription()
                + " " + pizza.getCost()+ currency);
        deliveryStrategy.deliver(pizza);
    }
}
