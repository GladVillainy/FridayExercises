package ek.dk.friday_2026_02_06;

public class PizzaFactory {

    public Pizza createPizza(String pizzaType){
        if (pizzaType.equalsIgnoreCase("Veggie")){
            return new VeggiePizza();
        } else
        if (pizzaType.equalsIgnoreCase("Margherita")){
            return new MargheritaPizza();
        }
        return null;
    }
}
