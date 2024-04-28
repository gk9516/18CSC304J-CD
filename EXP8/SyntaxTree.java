public class SyntaxTree {
    // Function to build syntax tree from expression
    static Node buildSyntaxTree(String expression) {
        return buildSyntaxTreeHelper(expression.toCharArray(), new int[] { 0 });
    }

    private static Node buildSyntaxTreeHelper(char[] expression, int[] index) {
        Node root = null;
        Node current = null;
        boolean isOperand = false;
        while (index[0] < expression.length) {
            char c = expression[index[0]];
            index[0]++;
            if (Character.isDigit(c)) {
                current = new Node(c);
                isOperand = true;
            } else {
                if (!isOperand) {
                    throw new IllegalArgumentException("Invalid expression");
                }
                root = new Node(c);
                root.left = current;
                root.right = buildSyntaxTreeHelper(expression, index);
                break;
            }
        }
        if (root == null) {
            return current;
        }
        return root;
    }

    // Function to evaluate syntax tree
    static int evaluateSyntaxTree(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return Character.getNumericValue(root.data);
        int leftValue = evaluateSyntaxTree(root.left);
        int rightValue = evaluateSyntaxTree(root.right);
        switch (root.data) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return leftValue / rightValue;
        }
        return 0;
    }

    static void printSyntaxTree(Node root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "│   ";
            }
            System.out.println(root.data);
            printSyntaxTree(root.left, indent, false);
            printSyntaxTree(root.right, indent, true);
        }
    }
}
