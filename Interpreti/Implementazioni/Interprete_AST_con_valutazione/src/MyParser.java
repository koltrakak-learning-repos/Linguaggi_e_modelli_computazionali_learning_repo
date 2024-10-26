import exp.*;

public class MyParser {
    private MyScanner scanner;
    private Token currentToken;

    public MyParser(MyScanner scanner) {
        this.scanner = scanner;

        this.currentToken = scanner.getNextToken(); //può essere null
    }

    public Exp parseExp() {
        Exp termSeq = parseTerm();

        while (currentToken != null) {
            if( currentToken.equals("+") ) {
                currentToken = scanner.getNextToken();

                Exp nextTerm = parseTerm();
                if (nextTerm != null) {
                    termSeq = new PlusExp(termSeq, nextTerm);
                    System.out.println("\tcostruisco termSeq: " + termSeq);
                }
                else
                    return null;    // opppure eccezione
                
            }
            else if( currentToken.equals("-") ) {
                currentToken = scanner.getNextToken();

                Exp nextTerm = parseTerm();
                if (nextTerm != null) {
                    termSeq = new MinusExp(termSeq, nextTerm);
                    System.out.println("\tcostruisco termSeq: " + termSeq);
                }
                else
                    return null;    // opppure eccezione
            }
            else {
                // next token non fa parte di L(Exp)
                return termSeq; 
            }
        } 

        // next token nullo -> end input
        return termSeq; 
    }

    public Exp parseTerm() {
        Exp powSeq = parsePow();

        while (currentToken != null) {
            if( currentToken.equals("*") ) {
                currentToken = scanner.getNextToken();

                Exp nextPow = parsePow();

                if (nextPow != null) {
                    powSeq = new MulExp(powSeq, nextPow);
                    System.out.println("\tcostruisco powSeq: " + powSeq);
                }
                else
                    return null;    // opppure eccezione
                //System.out.print("\tValuto "+t1+" + "+t2+" = ");    
            }
            else if( currentToken.equals("/") ) {
                currentToken = scanner.getNextToken();

                Exp nextPow = parsePow();

                if (nextPow != null) {
                    powSeq = new DivExp(powSeq, nextPow);
                    System.out.println("\tcostruisco powSeq: " + powSeq);
                }
                else
                    return null;    // opppure eccezione
            }
            else {
                // next token non fa parte di L(Term)
                return powSeq; 
            }
        }

        // next token nullo -> end input
        return powSeq; 
    }

    public Exp parsePow() {
        Exp factorSeq = parseFactor();
    
        if (currentToken != null) {
            if (currentToken.equals("^")) {
                currentToken = scanner.getNextToken();

                Exp nextFactor = parsePow();  // Chiamata ricorsiva per ottenere associatività a destra

                if (nextFactor != null) {
                    factorSeq = new PowExp(factorSeq, nextFactor);
                    System.out.println("\tcostruisco factorSeq: " + factorSeq);
                }
                else
                    return null;    // opppure eccezione
            } 
        }
        
        // next token nullo -> end input
        return factorSeq; 
    }

    public Exp parseFactor() {
        if (currentToken.equals("(")) {
            currentToken = scanner.getNextToken();

            Exp innerExp = parseExp(); // self-embedding

            if (currentToken != null && currentToken.equals(")")) {
                currentToken = scanner.getNextToken();

                System.out.println("\tconsidero InnerExp: " + innerExp);

                return innerExp; // parentesi irrilevanti
            } 
            else {
                throw new IllegalArgumentException("missing )");
            }
        }
        else { // dev’essere un numero
            if (currentToken.isNumber()) {
                int value = currentToken.getAsInt();
                System.out.println("\tconsidero il termine: "+value);

                currentToken = scanner.getNextToken();

                return new NumExp(value);
            }
            else {  // non è un fattore, quindi
                throw new IllegalArgumentException(currentToken + " not a number");
            }
        }
    }
}
