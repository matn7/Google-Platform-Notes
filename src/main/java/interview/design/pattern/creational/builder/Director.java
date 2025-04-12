package interview.design.pattern.creational.builder;

public class Director {

    void constructVeganMeal(Builder builder) {
        builder.addStarter();
        builder.addMainCourse();
        builder.addDessert();
        builder.addDrink();
    }

    void constructHealthyMeal(Builder builder) {
        builder.addStarter();
        builder.addMainCourse();
        builder.addDessert();
        builder.addDrink();
    }

}
