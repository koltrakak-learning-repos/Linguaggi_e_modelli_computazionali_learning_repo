import java.util.Stack;

public class MyParser {
    private MyScanner scanner;
    private Token currentToken;

    public MyParser(MyScanner scanner) {
        this.scanner = scanner;

        this.currentToken = scanner.getNextToken(); //può essere null
    }

    public int parseExp() {
        int t1 = parseTerm();

        while (currentToken != null) {
            if( currentToken.equals("+") ) {
                currentToken = scanner.getNextToken();

                int t2 = parseTerm();
                System.out.print("\tValuto "+t1+" + "+t2+" = ");
                t1 = t1 + t2;
                System.out.println(t1);
            }
            else if( currentToken.equals("-") ) {
                currentToken = scanner.getNextToken();

                int t2 = parseTerm();

                System.out.print("\tValuto "+t1+" - "+t2+" = ");
                t1 = t1 - t2;
                System.out.println(t1);
            }
            else {
                return t1; // next token non fa parte di L(Exp)
            }
        } 

        return t1; // next token nullo -> end input
    }

    /* ASSOCIATIVO A DESTRA (non ho bisogno di trasformare la ricorsione in iterazione!!!)*/
    // public int parseExp() {
    //     int t1 = parseTerm();
    
    //     if (currentToken != null) {
    //         if (currentToken.equals("+")) {
    //             currentToken = scanner.getNextToken();

    //             int t2 = parseExp();  
    //             System.out.print("\tValuto " + t1 + " + " + t2 + " = ");
    //             t1 = t1 + t2;
    //             System.out.println(t1);
    //         } 
    //         else if (currentToken.equals("-")) {
    //             currentToken = scanner.getNextToken();

    //             int t2 = parseExp();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.print("\tValuto " + t1 + " - " + t2 + " = ");
    //             t1 = t1 - t2;
    //             System.out.println(t1);
    //         }
    //     }
    
    //     return t1; // restituisce t1 alla fine
    // }

    public int parseTerm() {
        int p1 = parsePow();

        while (currentToken != null) {
            if( currentToken.equals("*") ) {
                currentToken = scanner.getNextToken();

                int p2 = parsePow();

                System.out.print("\tValuto "+p1+" * "+p2+" = ");
                p1 = p1 * p2;
                System.out.println(p1);
            }
            else if( currentToken.equals(":") ) {
                currentToken = scanner.getNextToken();

                int p2 = parsePow();
                
                System.out.print("\tValuto "+p1+" / "+p2+" = ");
                p1 = p1 / p2;
                System.out.println(p1);
            }
            else {
                return p1; // next token non fa parte di L(Term)
            }
        }

        return p1; // next token nullo -> end input
    }

    /* ASSOCIATIVO A DESTRA */
    // public int parseTerm() {
    //     int f1 = parseFactor();
    
    //     if (currentToken != null) {
    //         if (currentToken.equals("*")) {
    //             currentToken = scanner.getNextToken();

    //             int f2 = parseTerm();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.print("\tValuto " + f1 + " * " + f2 + " = ");
    //             f1 = f1 * f2;
    //             System.out.println(f1);
    //         } 
    //         else if (currentToken.equals(":")) {
    //             currentToken = scanner.getNextToken();

    //             int f2 = parseTerm();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.print("\tValuto " + f1 + " / " + f2 + " = ");
    //             f1 = f1 / f2;
    //             System.out.println(f1);
    //         }
    //     }
    
    //     return f1; // restituisce f1 alla fine
    // }

    // parse Pow con regola ricorsiva
    // public int parsePow() {
    //     int f1 = parseFactor();
    
    //     if (currentToken != null) {
    //         if (currentToken.equals("^")) {
    //             currentToken = scanner.getNextToken();

    //             int f2 = parsePow();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.print("\tValuto " + f1 + " ^ " + f2 + " = ");

    //             int tmp = f1;
    //             for(int i = 1; i < f2; i++) {
    //                 tmp = tmp * f1;
    //             }
    //             f1 = tmp;
    //             System.out.println(f1);
    //         } 
    //     }
    
    //     return f1; // restituisce f1 alla fine
    // }

    // parse Pow con regola iterativa (ho bisogno per forza di uno stack dato che sono associativo a destra)
    public int parsePow() {
        Stack<Integer> stack = new Stack<>();

        int f1 = parseFactor();
        stack.push(f1);  
        
        while (currentToken != null ) {
            if (currentToken.equals("^")) {
                currentToken = scanner.getNextToken();

                int f2 = parseFactor();
                stack.push(f2);  // Aggiunge il fattore successivo sulla pila
            }
            else {
                break; // next token non fa parte di L(Term)
            }
        }
        
        // Calcola la potenza con associatività a destra
        int result = stack.pop();
        while (!stack.isEmpty()) {
            int base = stack.pop();
            System.out.print("\tValuto " + base + " ^ " + result + " = ");
            
            int tmp = base;
            for (int i = 1; i < result; i++) {
                tmp *= base;
            }
            result = tmp;
            System.out.println(result);
        }
        
        return result;
    }

    public int parseFactor() {
        if (currentToken.equals("(")) {
            currentToken = scanner.getNextToken();
            int innerExp = parseExp(); // self-embedding

            if (currentToken != null && currentToken.equals(")")) {
                currentToken = scanner.getNextToken();
                return innerExp; // parentesi irrilevanti
            } 
            else {
                throw new IllegalArgumentException("missing )");
            }
        }
        else { // dev’essere un numero
            if (currentToken.isNumber()) {
                int value = currentToken.getAsInt();
                System.out.println("\tValuto "+value);

                currentToken = scanner.getNextToken();

                return value;
            }
            else {// non è un fattore, quindi
                throw new IllegalArgumentException(currentToken + " not a number");
            }
        }
    }
}
