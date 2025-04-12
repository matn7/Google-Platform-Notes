package interview.design.pattern.creational.builder.assignment;

public class Main {

    public static void main(String[] args) {
        MealBuilder builder = new MealBuilder();
        Meal myMeal = builder.addCost(15.99)
                .addTakeOut(true)
                .addMainCourse("Burger")
                .addDrink("Coke")
                .build();

        System.out.println(myMeal.getCost());       // Cost: 15.99
        System.out.println(myMeal.getTakeOut());    // TakeOut: true
        System.out.println(myMeal.getMain());       // Main: "Burger"
        System.out.println(myMeal.getDrink());      // Drink: "Coke"
    }

}
