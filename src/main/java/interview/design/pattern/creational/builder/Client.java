package interview.design.pattern.creational.builder;

public class Client {

    public static void main(String[] args) {
        Director director = new Director();
        VeganMealBuilder builder = new VeganMealBuilder();
//        director.constructVeganMeal(builder);

        builder.addDrink();
        builder.addStarter();

        Meal veganMeal = builder.build();

        System.out.println("Meal constructed: ");
        System.out.println("Starter: " + veganMeal.getStarter());
        System.out.println("Main: " + veganMeal.getMain());
        System.out.println("Dessert: " + veganMeal.getDessert());
        System.out.println("Drink: " + veganMeal.getDrink());

        HealthyMealBuilder builder2 = new HealthyMealBuilder();
        director.constructHealthyMeal(builder2);
        Meal healthyMeal = builder2.build();

        System.out.println("Meal constructed: ");
        System.out.println("Starter: " + healthyMeal.getStarter());
        System.out.println("Main: " + healthyMeal.getMain());
        System.out.println("Dessert: " + healthyMeal.getDessert());
        System.out.println("Drink: " + healthyMeal.getDrink());

    }

}
