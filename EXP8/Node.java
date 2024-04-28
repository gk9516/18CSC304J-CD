public class Node {
    char data;
    Node left, right;

    Node(char data) {
        this.data = data;
        left = right = null;
    }

    // Method to print the syntax tree
    void printTree(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + data);
        if (left != null && right != null) {
            left.printTree(prefix + (isTail ? "    " : "│   "), false);
            right.printTree(prefix + (isTail ? "    " : "│   "), true);
        } else if (left != null) {
            left.printTree(prefix + (isTail ? "    " : "│   "), true);
        } else if (right != null) {
            right.printTree(prefix + (isTail ? "    " : "│   "), true);
        }
    }
}
