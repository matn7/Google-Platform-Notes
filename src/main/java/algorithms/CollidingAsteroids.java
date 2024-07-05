package algorithms;

import java.util.Stack;

public class CollidingAsteroids {

    // O(n) time | O(n) space
    public static Stack<Integer> collidingAsteroids(int[] asteroids) {
        Stack<Integer> resultStack = new Stack<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                resultStack.push(asteroid);
                continue;
            }
            while (true) {
                if (resultStack.isEmpty() || resultStack.peek() < 0) {
                    resultStack.push(asteroid);
                    break;
                }
                int asteroidSize = Math.abs(asteroid);
                if (resultStack.peek() > asteroidSize) {
                    break;
                } if (resultStack.peek() == asteroidSize) {
                    resultStack.pop();
                    break;
                }
                resultStack.pop();
            }
        }
        return resultStack;
    }
}
