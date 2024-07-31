package algorithms;

import java.util.Arrays;

public class CountPrimes {

    public static void main(String[] args) {
        int result = countPrimes(10);
        System.out.println(result);
    }

    // O(n) time | O(n) space - Erathostenes Sieve
    public static int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        //  0  1  2  3  4  5  6  7  8  9
        // [f, f, t, t, f, t, f, t, f, t]
        //           *
        for (int i = 2; i < Math.ceil(Math.sqrt(n)); i++) {
            if (isPrime[i]) {
                for (int mulIdx = i * 2; mulIdx < n; mulIdx += i) {
                    isPrime[mulIdx] = false;
                }
            }
        }
        int count = 0;
        for (boolean prime : isPrime) {
            if (prime) {
                count++;
            }
        }
        return count;
    }

    // O(sqrt(n)) time | O(1) space
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        } else if (n == 2 || n == 3) {
            return true;
        } else if (n % 2 == 0 || n % 3 == 0) {
            return false;
        } else {
            for (int i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}