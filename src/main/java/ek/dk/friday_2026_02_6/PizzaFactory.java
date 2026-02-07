package ek.dk.friday_2026_02_6;

public class PizzaFactory {

    public void createPizza(String pizzaType){
        if (pizzaType.equalsIgnoreCase("Veggie")){
            Pizza veggie = new VeggiePizza();
            System.out.println(veggie.getDescription());
        } else
        if (pizzaType.equalsIgnoreCase("Margherita")){
            Pizza margherita = new MargheritaPizza();
            System.out.println(margherita.getDescription());
        }
    }
}
