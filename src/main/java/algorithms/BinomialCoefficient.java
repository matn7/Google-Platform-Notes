package algorithms;

public class BinomialCoefficient {

    // ********
    // * STAR *
    // ********

    // C(n,k) = n! / (k! * (n - k)!)

    // Function to calculate binomial coefficient C(n, k) using dynamic programming
    public static int binomialCoeff(int n, int k) {
        // Create a 2D array to store results of subproblems
        int[][] C = new int[n + 1][k + 1];

        // Calculate binomial coefficient for each value
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                // Base case: C(i, 0) = C(i, i) = 1
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    // Recurrence relation: C(i, j) = C(i - 1, j - 1) + C(i - 1, j)
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }

        return C[n][k]; // Return the value of C(n, k)
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 2;

        // Calculate C(n, k)
        System.out.println("Binomial Coefficient C(" + n + ", " + k + ") is " + binomialCoeff(n, k));
    }
}