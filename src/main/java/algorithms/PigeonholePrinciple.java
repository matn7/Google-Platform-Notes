package algorithms;

import java.util.HashSet;

public class PigeonholePrinciple {

    // ********
    // * STAR *
    // ********

    // Function to check for duplicates in an array using Pigeonhole Principle
    public static boolean hasDuplicates(int[] arr, int range) {
        // Create a HashSet to store encountered elements
        HashSet<Integer> seen = new HashSet<>();

        // Iterate over the array
        for (int num : arr) {
            // If the number is out of the expected range, we can immediately return false
            if (num < 0 || num >= range) {
                System.out.println("Element out of expected range!");
                return false;
            }

            // If the number has already been seen, we have a duplicate
            if (seen.contains(num)) {
                return true; // Found a duplicate
            }

            // Add the number to the set of seen elements
            seen.add(num);
        }

        // If we finish the loop without finding duplicates, return false
        return false;
    }

    public static void main(String[] args) {
        // Example array
        int[] arr = {1, 2, 3, 4, 5, 1}; // Has duplicate 1
        int range = 6;  // Expected range of values is [0, 5]

        // Check for duplicates using the Pigeonhole Principle
        if (hasDuplicates(arr, range)) {
            System.out.println("The array contains duplicates.");
        } else {
            System.out.println("The array does not contain duplicates.");
        }
    }
}