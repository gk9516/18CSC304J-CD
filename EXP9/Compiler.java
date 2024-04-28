class Compiler {
    private static int labelCounter = 0;

    public static void main(String[] args) {
        String sourceCode = "if(x<10){y=5;}";
        String intermediateCode = generateIntermediateCode(sourceCode);
        System.out.println("Intermediate Code:");
        System.out.println(intermediateCode);
    }

    public static String generateIntermediateCode(String sourceCode) {
        // Parse source code and generate intermediate code for If statements
        StringBuilder intermediateCode = new StringBuilder();

        // Remove whitespace to simplify tokenization
        sourceCode = sourceCode.replaceAll("\\s+", "");

        // Check if source code contains an If statement
        if (sourceCode.startsWith("if(") && sourceCode.contains("){")) {
            // Extract condition and statement block
            int conditionEndIndex = sourceCode.indexOf(")");
            String condition = sourceCode.substring(3, conditionEndIndex);
            String statementBlock = sourceCode.substring(conditionEndIndex + 2, sourceCode.indexOf("}"));

            // Generate labels
            String labelTrue = generateLabel();
            String labelAfterIf = generateLabel();

            // Generate intermediate code for the condition
            String[] conditionParts = condition.split("<|>|==");
            String operand1 = conditionParts[0];
            String operator = condition.replaceAll("[^<>=]", "");
            String operand2 = conditionParts[1];
            intermediateCode.append("if ").append(operand1).append(" ").append(operator).append(" ").append(operand2).append(" goto ").append(labelTrue).append("\n");
            intermediateCode.append("goto ").append(labelAfterIf).append("\n");
            intermediateCode.append(labelTrue).append(":\n");

            // Generate intermediate code for the statement block
            String[] statements = statementBlock.split(";");
            for (String statement : statements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    String[] assignment = statement.split("=");
                    String variable = assignment[0];
                    String value = assignment[1];
                    intermediateCode.append(variable).append(" = ").append(value).append("\n");
                }
            }

            intermediateCode.append(labelAfterIf).append(":\n");
        } else {
            intermediateCode.append("No If statement found in source code.");
        }

        return intermediateCode.toString();
    }

    public static String generateLabel() {
        // Generate label by incrementing counter
        return "L" + (labelCounter++);
    }
}
