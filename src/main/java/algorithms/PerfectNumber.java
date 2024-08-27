package algorithms;

import java.util.ArrayList;
import java.util.List;

public class PerfectNumber {

    public static void main(String[] args) {
        int number = 28;

        for (int i = 1; i < 10000; i++) {
            if (perfectNumber(i)) {
                System.out.println(i + " isPerfectNumber: " + perfectNumber(i));
            }
        }
    }

    // O(n) time | O(n) space
    public static boolean perfectNumber(int number) {
        if (number <= 0) {
            return false;
        }
        // 28
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }
        int sum = 0;
        for (Integer div : divisors) {
            sum += div;
        }
        return sum == number;
    }

    // Funkcja obliczająca sumę właściwych dzielników liczby n
    public static int sumOfDivisors(int n) {
        int sum = 1; // 1 jest zawsze dzielnikiem
        int sqrtN = (int) Math.sqrt(n);

        // Obliczanie dzielników
        for (int i = 2; i <= sqrtN; i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) {
                    sum += n / i;
                }
            }
        }

        return sum;
    }

    // Funkcja znajdująca liczby doskonałe w zadanym zakresie
    public static void findPerfectNumbers(int maxNumber) {
        for (int i = 2; i <= maxNumber; i++) {
            if (sumOfDivisors(i) == i) {
                System.out.println(i + " jest liczbą doskonałą.");
            }
        }
    }

}
