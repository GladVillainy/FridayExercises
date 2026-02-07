# Pizza Ordering System 
## Design Patterns Exercise
### Goal
Build a Pizza Ordering System using **Factory**, **Decorator**, and **Strategy** patterns.  
You will work step by step to create pizzas, add toppings, and choose delivery methods.  
At the end, you’ll have a working console program.

---

### Factory pattern - Steps
1. Implement `MargheritaPizza` and `VeggiePizza` (`getDescription`, `getCost`). 
2. Implement `PizzaFactory.createPizza(String type)`. 
3. Test by creating pizzas in `PizzaDemo` and printing description + cost.

#### Example output:
```text
Margherita Pizza $5.0
Veggie Pizza $6.0
```

### Decorator pattern - Steps
1. Implement `CheeseTopping` and `PepperoniTopping`.
2. Override `getDescription` and `getCost`.
3. Wrap pizzas with toppings in `PizzaDemo`.

#### Example output:
```text
Veggie Pizza, Cheese $7.0
Margherita Pizza, Cheese, Pepperoni $7.5
```

### Strategy pattern - Steps:
1. Implement `HomeDelivery`, `Pickup`, `DroneDelivery` (print different messages).
2. Implement `Order.processOrder()` to delegate to strategy.
3. Test by creating an `Order` with a pizza + delivery strategy.

#### Example output:
```text
Order: Margherita Pizza, Cheese, Pepperoni $7.5
Delivery method: Home Delivery selected. Your pizza will arrive soon!
```

### Integration & Extensions - Steps:
- Build a **console flow** in `PizzaDemo`:
    1. Ask the user which pizza they want (Factory).
    2. Ask if they want to add toppings (loop until done).
    3. Ask how they want it delivered (Strategy).
    4. Print final receipt (pizza description, cost, delivery method).

---

### Extra Challenges (Optional)
- Add new toppings (`OlivesTopping`, `MushroomsTopping`).
- Add new delivery method (`ExpressDelivery` with surcharge).
- Add a `DiscountStrategy` (10% off coupon).
- Add a `ComboPizzaFactory` (meal deals with drink).
