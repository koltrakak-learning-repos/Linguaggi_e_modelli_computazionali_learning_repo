import exp.*;

public class MyParser {
    private MyScanner scanner;
    private Token currentToken;

    public MyParser(MyScanner scanner) {
        this.scanner = scanner;

        this.currentToken = scanner.getNextToken(); //può essere null
    }

    public Exp parseExp() {
        /*
         * Per prima cosa controlla se sto processando una espressione di assegnamento
         * oppure, una normale espressione aritmetica
         */
        if (currentToken.isIdentifier()) {
            String id = currentToken.toString();
            currentToken = scanner.getNextToken();

            if ( !(currentToken.equals("=")) ) {
                throw new IllegalArgumentException("espressione di assegnamento con LIdent senza \"=\"");
            }

            currentToken = scanner.getNextToken();
            Exp rightExp = parseExp();
            
            Exp assignExp = new AssignExp(new LIdentExp(id), rightExp);
            System.out.println("parseExp: costruisco AssignExp: " + assignExp);

            return assignExp;
        } 
        // parsing di una espressione aritmetica
        else {
            Exp termSeq = parseTerm();

            while (currentToken != null) {
                if( currentToken.equals("+") ) {
                    currentToken = scanner.getNextToken();

                    Exp nextTerm = parseTerm();
                    if (nextTerm != null) {
                        termSeq = new PlusExp(termSeq, nextTerm);
                        System.out.println("parseExp: costruisco termSeq: " + termSeq);
                    }
                    else
                        return null;    // opppure eccezione 
                }
                else if( currentToken.equals("-") ) {
                    currentToken = scanner.getNextToken();

                    Exp nextTerm = parseTerm();
                    if (nextTerm != null) {
                        termSeq = new MinusExp(termSeq, nextTerm);
                        System.out.println("parseExp: costruisco termSeq: " + termSeq);
                    }
                    else
                        return null;    // opppure eccezione
                }
                else {
                    // next token non fa parte di L(Exp)
                    System.out.println("parseExp: non riconosco \"" + currentToken + "\" perciò ritorna");
                    return termSeq; 
                }
            }

            // next token nullo -> end input
            return termSeq; 
        }
    }

    /*
     * Questa versione è quella delle slide. Funziona, ma è un po incasinata.
     * In particolare riesce a fare il parsing dei LIdent solamente perchè c'è una catena
     * di restituzioni di null che arriva fino a parseTerm.
     * 
     * Se parsefactor lanciasse eccezione quando si ritrova un token che non sa come gestire
     * questa implementazione non funzionerebbe
     */
    // public Exp parseExp() {
    //     Exp termSeq = parseTerm();

    //     while (currentToken != null) {
    //         if( currentToken.equals("+") ) {
    //             currentToken = scanner.getNextToken();

    //             Exp nextTerm = parseTerm();
    //             if (nextTerm != null) {
    //                 termSeq = new PlusExp(termSeq, nextTerm);
    //                 System.out.println("\tcostruisco termSeq: " + termSeq);
    //             }
    //             else
    //                 return null;    // opppure eccezione 
    //         }
    //         else if( currentToken.equals("-") ) {
    //             currentToken = scanner.getNextToken();

    //             Exp nextTerm = parseTerm();
    //             if (nextTerm != null) {
    //                 termSeq = new MinusExp(termSeq, nextTerm);
    //                 System.out.println("\tcostruisco termSeq: " + termSeq);
    //             }
    //             else
    //                 return null;    // opppure eccezione
    //         }
    //         else if (currentToken.isIdentifier()) {
    //             String id = currentToken.toString();
    //             currentToken = scanner.getNextToken();

    //             if ( !(currentToken.equals("=")) )
    //                 return null;    // oppure eccezione

    //             currentToken = scanner.getNextToken();
    //             Exp rightExp = parseExp();
                
    //             Exp completeAssignExp = new AssignExp(new LIdentExp(id), rightExp);
    //             System.out.println("\tcostruisco termSeq: " + completeAssignExp);

    //             return completeAssignExp;
    //         }
    //         else {
    //             // next token non fa parte di L(Exp)
    //             System.out.println("\tparseExp non riconosce: " + currentToken + " perciò ritorna");
    //             return termSeq; 
    //         }
    //     } 

    //     // next token nullo -> end input
    //     return termSeq; 
    // }

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
                System.out.println("\tparseTerm non riconosce: " + currentToken + " perciò ritorna");
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
        else if (currentToken.equals("$")) {
            //mangio l'identificatore dopo $
            currentToken = scanner.getNextToken();
            String id = currentToken.toString();

            currentToken = scanner.getNextToken();

            Exp rIdent = new RIdentExp(id);
            System.out.println("\tconsidero rIdent: " + rIdent);
            
            return rIdent;
        }
        // dev’essere un numero
        else if (currentToken.isNumber()) {
            int value = currentToken.getAsInt();
            System.out.println("\tconsidero il termine: "+value);

            currentToken = scanner.getNextToken();

            return new NumExp(value);
        }
        else {  
            // non si è costruito nulla, restituiamo null   
            return null;
        }
    }
}
