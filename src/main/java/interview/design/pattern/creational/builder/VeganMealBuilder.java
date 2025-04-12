package interview.design.pattern.creational.builder;

public class VeganMealBuilder implements Builder {

    private Meal meal;

    public VeganMealBuilder() {
        this.meal = new Meal();
    }

    @Override
    public void addStarter() {
        meal.setStarter(Starter.SALAD);
    }

    @Override
    public void addMainCourse() {
        meal.setMain(Main.VEGGIE_STIR_FRY);
    }

    @Override
    public void addDessert() {
        meal.setDessert(Dessert.VEGAN_PUDDING);
    }

    @Override
    public void addDrink() {
        meal.setDrink(Drink.VEGAN_SHAKE);
    }

    Meal build() {
        return this.meal;
    }
}
