package algorithms;

class RangeSumQuery {
    int[] segmentTree;
    int n;  // Size of the input array

    public RangeSumQuery(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            segmentTree = new int[2 * n];  // Segment tree size is 2n
            buildSegmentTree(nums);
        }
    }

    // O(n) time
    // Build the segment tree from the input array
    private void buildSegmentTree(int[] nums) {
        // Initialize leaf nodes
        for (int i = 0; i < n; i++) {
            segmentTree[n + i] = nums[i];
        }
        // Build the internal nodes (parent nodes) by summing children nodes
        for (int i = n - 1; i > 0; --i) {
            segmentTree[i] = segmentTree[2 * i] + segmentTree[2 * i + 1];
        }
    }

    // O(log(n)) time | O(n) space
    // Update the value at a specific index in the array
    public void update(int index, int value) {
        // Update the value at the corresponding leaf node
        index += n;  // Shift index to the leaf node
        segmentTree[index] = value;

        // Propagate the update upwards
        while (index > 1) {
            index /= 2;
            segmentTree[index] = segmentTree[2 * index] + segmentTree[2 * index + 1];
        }
    }

    // O(log(n)) time |
    // Get the sum of elements in the range [left, right]
    public int sumRange(int left, int right) {
        int sum = 0;
        left += n;  // Shift left index to the leaf level
        right += n;  // Shift right index to the leaf level

        while (left <= right) {
            // If left is a right child, include it and move to the parent's right sibling
            if ((left % 2) == 1) {
                sum += segmentTree[left];
                left++;
            }
            // If right is a left child, include it and move to the parent's left sibling
            if ((right % 2) == 0) {
                sum += segmentTree[right];
                right--;
            }
            // Move to the parent nodes
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(nums);

        System.out.println(rangeSumQuery.sumRange(1, 3));  // Output: 15 (3 + 5 + 7)
        rangeSumQuery.update(1, 10);  // Update index 1 to value 10
        System.out.println(rangeSumQuery.sumRange(1, 3));  // Output: 22 (10 + 5 + 7)
    }
}
