import java.io.*;
import java.util.*;

public class SLR_parser {

    //static char lookahead;
    public static void main(String[] args) throws IOException {

        File input = new File("input.txt");
        Scanner read = new Scanner(input);

        if (!input.exists()) {
            System.out.println(input + " doesn't exist");
            System.exit(0);
        }
        String proudctions[] = {"E'->E", "E->E+T", "E->T", "T->T*F", "T->F", "F->(E)", "F->id"};
        //                     id    +    *    (    )     $
        String action[][] = {{"S5","N","N", "S4", "N", "N"} //stste 0
        ,
         {"N", "S6", "N", "N", "N", "Acc"}//stste 1
        ,
         {"N", "R2", "S7", "N", "R2", "R2"} //state 2
        ,
         {"N", "R4", "R4", "N", "R4", "R4"} //state 3  
        ,
         {"S5", "N", "N", "S4", "N", "N"} //state 4 
        ,
         {"N", "R6", "R6", "N", "R6", "R6"} //state 5  
        ,
         {"S5", "N", "N", "S4", "N", "N"} //state 6
        ,
         {"S5", "N", "N", "S4", "N", "N"} //state 7
        ,
         {"N", "S6", "N", "N", "S11", "N"}//stste 8
        ,
         {"N", "R1", "S7", "N", "R1", "R1"} //state 9 
        ,
         {"N", "R3", "R3", "N", "R3", "R3"} //state 10 
        ,
         {"N", "R5", "R5", "N", "R5", "R5"} /state 11/};

        //                     E    T    F
        String Goto[][] = {{"1", "2", "3"} //state 0
        ,
         {"N", "N", "N"}//state 1
        ,
         {"N", "N", "N"}//state 2
        ,
         {"N", "N", "N"}//state 3
        ,
         {"8", "2", "3"} //state 4
        ,
         {"N", "N", "N"}//state 5
        ,
         {"N", "9", "3"}//state 6
        ,
         {"N", "N", "10"}//state 7
        ,
         {"N", "N", "N"}//state 8
        ,
         {"N", "N", "N"}//state 9
        ,
         {"N", "N", "N"}//state 10
        ,
         {"N", "N", "N"}//state 11
        };

        while (read.hasNext()) {
            String w = read.nextLine();
            System.out.println("Right most derivation for the arithmetic expression " + w);
            System.out.println("");
            SLR(w, action, Goto, proudctions);
        }
    }

    public static void SLR(String w, String[][] action, String[][] Goto, String proudctions[]) {

        String[] ip = w.split(" ");
        Stack<String> stack = new Stack<>();
        stack.push("0");
        int s = 0;
        
            while (true) {
                
                for (int i = 0; i < ip.length; i++) {
                char check =action[s][getIndex(ip[i])].charAt(0);                    
                if (check == 'S') {
                    stack.push(ip[i]);
                    stack.push(action[s][getIndex(ip[i])].substring(1));
                    s = Integer.valueOf(action[s][getIndex(ip[i])].substring(1));
                    System.out.println("Shift " + s);
                    
                } else if (check == 'R') {
                    
                    int Pnum = Integer.valueOf(action[s][getIndex(ip[i])].substring(1));
                    int numOfSymbols;
                    if (Pnum == 6) {
                        numOfSymbols = 1;
                    } else {
                        numOfSymbols = (proudctions[Pnum].length() - 3);
                    }
                    
                    for (int j = 0; j < numOfSymbols*2; j++) {
                        stack.pop();
                    }
                    s = Integer.valueOf(stack.peek());
                    stack.push(proudctions[Pnum].substring(0, 1));
                    stack.push(Goto[s][getIndex(stack.peek())]);
                    s = Integer.valueOf(stack.peek());
                    System.out.println("Reduce by "+proudctions[Pnum]);
                    i--;
                }else if(check == 'A'){
                    System.out.println("Accept");
                    System.out.println("\n");
                    return;
                }else{
                    System.out.println("Syntax Error");
                    System.exit(0);
                }
            }

        }
    }

    public static int getIndex(String ip) {

        if (ip.equals("id")) {
            return 0;
        } else if (ip.equals("+")) {
            return 1;
        } else if (ip.equals("*")) {
            return 2;
        } else if (ip.equals("(")) {
            return 3;
        } else if (ip.equals(")")) {
            return 4;
        } else if (ip.equals("$")) {
            return 5;
        } else if (ip.equals("E")) {
            return 0;
        } else if (ip.equals("T")) {
            return 1;
        } else if (ip.equals("F")) {
            return 2;
        }

        return -1;
    }
}