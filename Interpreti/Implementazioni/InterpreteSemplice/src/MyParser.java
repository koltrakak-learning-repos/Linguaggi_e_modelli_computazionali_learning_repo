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
        int f1 = parseFactor();

        while (currentToken != null) {
            if( currentToken.equals("*") ) {
                currentToken = scanner.getNextToken();

                int f2 = parseFactor();

                System.out.print("\tValuto "+f1+" * "+f2+" = ");
                f1 = f1 * f2;
                System.out.println(f1);
            }
            else if( currentToken.equals(":") ) {
                currentToken = scanner.getNextToken();

                int f2 = parseFactor();
                
                System.out.print("\tValuto "+f1+" / "+f2+" = ");
                f1 = f1 / f2;
                System.out.println(f1);
            }
            else {
                return f1; // next token non fa parte di L(Term)
            }
        }

        return f1; // next token nullo -> end input
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
