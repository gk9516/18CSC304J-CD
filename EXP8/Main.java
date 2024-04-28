import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Read expression from file
        String expression = "";
        try {
            File file = new File("expression.txt");
            Scanner scanner = new Scanner(file);
            expression = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        // Build syntax tree and evaluate expression
        Node root = SyntaxTree.buildSyntaxTree(expression);
        System.out.println("Syntax Tree:");
        SyntaxTree.printSyntaxTree(root, "", true);
        int result = SyntaxTree.evaluateSyntaxTree(root);
        System.out.println("Expression: " + expression);
        System.out.println("Result: " + result);
    }
}
