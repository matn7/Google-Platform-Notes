package interview.design.pattern.creational.builder;

public class HealthyMealBuilder implements Builder {

    private Meal meal;

    public HealthyMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public void addStarter() {
        this.meal.setStarter(Starter.SALAD);
    }

    @Override
    public void addMainCourse() {
        this.meal.setMain(Main.GRILLED_CHICKEN);
    }

    @Override
    public void addDessert() {
        this.meal.setDessert(Dessert.FRUIT_SALAD);
    }

    @Override
    public void addDrink() {
        this.meal.setDrink(Drink.WATER);
    }

    Meal build() {
        return this.meal;
    }
}
