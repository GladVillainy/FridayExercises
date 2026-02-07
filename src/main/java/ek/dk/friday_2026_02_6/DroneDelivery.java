package ek.dk.friday_2026_02_6;

public class DroneDelivery implements DeliveryStrategy {
    @Override
    public void deliver(Pizza pizza) {
        System.out.println(
                "Your "+ pizza.getDescription() +
                " is on its way by drone - estimated delivery time 10 minutes!"
        );

    }
}
