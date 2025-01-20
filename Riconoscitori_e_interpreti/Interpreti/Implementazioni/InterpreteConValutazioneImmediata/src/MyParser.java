/*
 * Grammatica:
 * 
 * EXP  ::= TERM { ( + | - ) TERM }
 * 
 * TERM ::= FACTOR { ( * | : ) FACTOR }
 * 
 * FACTOR ::= num
 * FACTOR ::= ( EXP )
 */


// import java.util.Stack;

public class MyParser {
    private MyScanner scanner;
    // cursore sulla stringa di token da parsare
    private Token currentToken;

    public MyParser(MyScanner scanner) {
        this.scanner = scanner;
        this.currentToken = scanner.getNextToken(); //può essere null
    }

    /*
     * Si occupa solamente di sommare/sottrarre TERM tra loro
     */
    public int parseExp() {
        System.out.println("\tparseExp: currentToken = " + currentToken);
        // sicuramento devo analizzare un termine
        int t1 = parseTerm();

        // successivamente controllo se c'è qualcos'altro da analizzare, e se si
        // analizzo finche non rimane nulla (oppure trovo qualcosa di non pertinente
        // al mio sotto-linguaggio all'insegna dell'approccio prudente)
        while (currentToken != null) {
            if( currentToken.equals("+") ) {
                currentToken = scanner.getNextToken();
                System.out.println("\tparseExp: considero +; nextToken = " + currentToken);

                int t2 = parseTerm();
                System.out.println("\tparseExp: Valuto "+t1+" + "+t2+" = " + (t1+t2));
                // ho riconosciuto una frase valida per il sottolinguaggio di questo strato;
                // applico la semantica
                t1 = t1 + t2;
            }
            else if( currentToken.equals("-") ) {
                currentToken = scanner.getNextToken();
                System.out.println("\tparseExp: considero -; nextToken = " + currentToken);

                int t2 = parseTerm();
                System.out.println("\tparseExp: Valuto "+t1+" - "+t2+" = " + (t1-t2));
                // ho riconosciuto una frase valida per il sottolinguaggio di questo strato;
                // applico la semantica
                t1 = t1 - t2;
            }
            else {
                System.out.println("\tparseExp: currentToken = " + currentToken + "; ritorno il primo termine che ho letto, t1 = " + t1);
                return t1; // next token non fa parte di L(Exp)
            }
        }

        return t1; // next token nullo -> end input -> restituisco il risultato ottenuto
    }

    /* 
     * Versione associativa a destra!
     * Non ho bisogno di trasformare la ricorsione in iterazione.
     * La ricorsione destra è gia compatibile con LL(1) 
     * 
     * Oppure potrei metter tutti i termini che trovo iterativamente (come sopra) 
     * in una coda, e valutarli in ordine
     */
    // public int parseExp() {
    //     int t1 = parseTerm();
    //     // la roba qua sotto potrebbe essere una chiamata ad un nuovo metasimbolo AFTERTERM
    //     if (currentToken != null) {
    //         if (currentToken.equals("+")) {
    //             currentToken = scanner.getNextToken();

    //             int t2 = parseExp();  
    //             System.out.println("\tValuto " + t1 + " + " + t2 + " = ");
    //             t1 = t1 + t2;
    //             System.out.println(t1);
    //         } 
    //         else if (currentToken.equals("-")) {
    //             currentToken = scanner.getNextToken();

    //             int t2 = parseExp();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.println("\tValuto " + t1 + " - " + t2 + " = ");
    //             t1 = t1 - t2;
    //             System.out.println(t1);
    //         }
    //     }
    
    //     return t1; // restituisce t1 alla fine
    // }

    public int parseTerm() {
        System.out.println("\tparseTerm: currentToken = " + currentToken);
        int p1 = parsePow();

        while (currentToken != null) {
            if( currentToken.equals("*") ) {
                currentToken = scanner.getNextToken();
                System.out.println("\tparseTerm: considero *; nextToken = " + currentToken);

                int p2 = parsePow();
                System.out.println("\tparseTerm: Valuto "+p1+" * "+p2+" = " + (p1*p2));
                p1 = p1 * p2;
            }
            else if( currentToken.equals("/") ) {
                currentToken = scanner.getNextToken();
                System.out.println("\tparseTerm: considero /; nextToken = " + currentToken);

                int p2 = parsePow();
                System.out.println("\tparseTerm: Valuto "+p1+" / "+p2+" = " + (p1/p2));
                p1 = p1 / p2;
            }
            else {
                System.out.println("\tparseTerm: currentToken = " + currentToken + "; ritorno il primo termine che ho letto, p1 = " + p1);
                return p1; // next token non fa parte di L(Term)
            }
        }

        return p1; // next token nullo -> end input -> restituisco il risultato ottenuto
    }

    /* ASSOCIATIVO A DESTRA */
    // public int parseTerm() {
    //     int f1 = parseFactor();
    
    //     if (currentToken != null) {
    //         if (currentToken.equals("*")) {
    //             currentToken = scanner.getNextToken();

    //             int f2 = parseTerm();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.println("\tValuto " + f1 + " * " + f2 + " = ");
    //             f1 = f1 * f2;
    //             System.out.println(f1);
    //         } 
    //         else if (currentToken.equals(":")) {
    //             currentToken = scanner.getNextToken();

    //             int f2 = parseTerm();  // Chiamata ricorsiva per ottenere associatività a destra
    //             System.out.println("\tValuto " + f1 + " / " + f2 + " = ");
    //             f1 = f1 / f2;
    //             System.out.println(f1);
    //         }
    //     }
    
    //     return f1; // restituisce f1 alla fine
    // }

    public int parsePow() {
        System.out.println("\tparsePow: currentToken = " + currentToken);
        int f1 = parseFactor();
    
        // questo if mi serve dato che non posso invocare equals su null
        if (currentToken != null) {
            if (currentToken.equals("^")) {
                currentToken = scanner.getNextToken();
                System.out.println("\tparsePow: considero ^; nextToken = " + currentToken);

                int f2 = parsePow();  // Chiamata ricorsiva per ottenere associatività a destra

                int tmp = f1;
                for(int i = 1; i < f2; i++) {
                    tmp = tmp * f1;
                }
                System.out.println("\t\tValuto " + f1 + " ^ " + f2 + " = " + tmp);
                f1 = tmp;
            } 
            else {
                System.out.println("\tparsePow: currentToken = " + currentToken + "; ritorno il primo termine che ho letto, f1 = " + f1);
                return f1; // next token non fa parte di L(Term)
            }
        }
    
        return f1; // next token nullo -> end input -> restituisco il risultato ottenuto
    }

    // parse Pow con regola iterativa (ho bisogno per forza di uno stack dato che sono associativo a destra)
    // public int parsePow() {
    //     Stack<Integer> stack = new Stack<>();

    //     int f1 = parseFactor();
    //     stack.push(f1);  
        
    //     while (currentToken != null ) {
    //         if (currentToken.equals("^")) {
    //             currentToken = scanner.getNextToken();

    //             int f2 = parseFactor();
    //             stack.push(f2);  // Aggiunge il fattore successivo sulla pila
    //         }
    //         else {
    //             break; // next token non fa parte di L(Term)
    //         }
    //     }
        
    //     // Calcola la potenza con associatività a destra
    //     int result = stack.pop();
    //     while (!stack.isEmpty()) {
    //         int base = stack.pop();
    //         System.out.println("\tValuto " + base + " ^ " + result + " = ");
            
    //         int tmp = base;
    //         for (int i = 1; i < result; i++) {
    //             tmp *= base;
    //         }
    //         result = tmp;
    //         System.out.println(result);
    //     }
        
    //     return result;
    // }

    public int parseFactor() {
        System.out.println("\tparseFactor: currentToken = " + currentToken);

        // questo if mi serve dato che non posso invocare equals su null
        if (currentToken != null) {
            if (currentToken.equals("(")) {
                System.out.println("\tparseFactor: trovata (");
                currentToken = scanner.getNextToken();
                int innerExp = parseExp(); // self-embedding
                System.out.println("\tparseFactor: espressione interna = "+innerExp);

                if (currentToken != null && currentToken.equals(")")) { // mi sta dando fastidio il fatto di poter avere dei token null
                    System.out.println("\tparseFactor: trovata )");
                    currentToken = scanner.getNextToken();
                    return innerExp; // parentesi irrilevanti
                } 
                else {
                    throw new IllegalArgumentException("missing )");
                }
            }
            else if (currentToken.isNumber()) {
                int value = currentToken.getAsInt();
                System.out.println("\tparseFactor: valuto "+value);

                currentToken = scanner.getNextToken();
                return value;
            }
            else {// non è un fattore, quindi
                throw new IllegalArgumentException(currentToken + " not a number");
            }
        }

        // qua finisco solo se manca un operando
        throw new IllegalArgumentException("missing operando");
    }
}
