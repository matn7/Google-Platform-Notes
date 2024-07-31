package algorithms;

import java.math.BigInteger;

public class Factorial {

    public static void main(String[] args) {
        BigInteger factorial = factorial(123);
        System.out.println(factorial);
    }

    // O(n^2 log^2(n)) time | O(nlog(n)) space
    public static  BigInteger factorial(long n) {
        BigInteger tempResult = BigInteger.ONE;

        for (long i = n; i > 0; i--) {
            tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
        }

        return tempResult;
    }

    // O(n) time | O(1) space
    public static int factorialTailRecursion(int number) {
        int result = factorialTailRecursionHelper(number, 1);
        return result;
    }

    private static int factorialTailRecursionHelper(int number, int result) {
        if (number == 0) {
            return result;
        }
        return factorialTailRecursionHelper(number - 1, result * number);
    }
}

