package ek.dk.friday_2026_02_06;

public class HomeDelivery implements DeliveryStrategy {
    @Override
    public void deliver(Pizza pizza) {
        System.out.println(
                "Your "+ pizza.getDescription() +
                " will be delivered to your door within 30 minutes!"
        );

    }
}
