package algorithms;

import java.math.BigInteger;

public class CatalanNumbers {

    // Metoda rekurencyjna do obliczania liczby Catalana (niewydajna dla dużych n)
    public static long catalanRecursive(int n) {
        if (n <= 1) {
            return 1;
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalanRecursive(i) * catalanRecursive(n - 1 - i);
        }

        return result;
    }

    // Metoda dynamiczna do obliczania liczby Catalana
    public static long catalanDynamic(int n) {
        long[] catalan = new long[n + 1];
        catalan[0] = 1;

        for (int i = 1; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - 1 - j];
            }
        }

        return catalan[n];
    }

    // Metoda do obliczania liczby Catalana za pomocą wzoru zamkniętego
    public static BigInteger catalanClosedForm(int n) {
        BigInteger numerator = factorial(2 * n);
        BigInteger denominator = factorial(n + 1).multiply(factorial(n));
        return numerator.divide(denominator);
    }

    // Metoda do obliczania silni (factorial) za pomocą BigInteger
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;

        // Obliczanie liczby Catalana metodą rekurencyjną
        System.out.println("Catalan number C(" + n + ") [Recursive]: " + catalanRecursive(n));

        // Obliczanie liczby Catalana metodą dynamiczną
        System.out.println("Catalan number C(" + n + ") [Dynamic]: " + catalanDynamic(n));

        // Obliczanie liczby Catalana za pomocą wzoru zamkniętego
        System.out.println("Catalan number C(" + n + ") [Closed Form]: " + catalanClosedForm(n));
    }
}
