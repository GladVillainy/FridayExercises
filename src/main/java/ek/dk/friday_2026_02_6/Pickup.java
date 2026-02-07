package ek.dk.friday_2026_02_6;

public class Pickup implements DeliveryStrategy {
    @Override
    public void deliver(Pizza pizza) {
        System.out.println(
                "Your "+ pizza.getDescription() +
                " is ready for pickup at the restaurant in 15 minutes!"
        );
    }
}
