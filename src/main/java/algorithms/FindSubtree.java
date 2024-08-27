package algorithms;

public class FindSubtree {

    public static void main(String[] args) {
        Node a = new Node(1);
        a.left = new Node(4);
        a.left.left = new Node(3);
        a.left.left.left = new Node(8);
        a.left.left.right = new Node(8);
        a.left.right = new Node(2);
        a.left.right.left = new Node(8);
        a.left.right.right = new Node(8);
        a.right = new Node(5);
        a.right.left = new Node(4);
        a.right.right = new Node(5);

        Node b = new Node(4);
        b.left = new Node(3);
        b.right = new Node(2);

        boolean result = findSubtree(a, b);
        System.out.println(result);

    }

    // O(n) time | O(n) space
    public static boolean findSubtree(Node a, Node b) {
        if (a == null) {
            return false;
        }

        boolean valueMatch = a.value == b.value;
        if (valueMatch) {
            boolean leftMatch = (a.left == null || b.left == null) || findSubtree(a.left, b.left);
            if (leftMatch) {
                boolean rightMatch = (a.right == null || b.right == null) || findSubtree(a.right, b.right);
                if (rightMatch) {
                    return true;
                }
            }
        }
        return findSubtree(a.left, b) || findSubtree(a.right, b);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

}
