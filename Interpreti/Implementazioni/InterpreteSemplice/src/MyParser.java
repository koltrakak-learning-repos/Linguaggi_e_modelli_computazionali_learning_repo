public class MyParser {
    private MyScanner scanner;
    private Token currentToken;

    public MyParser(MyScanner scanner) {
        this.scanner = scanner;

        this.currentToken = scanner.getNextToken(); //può essere null
    }

    public int parseExp() {
System.out.println("parseEXP:\tcurrent token = " + this.currentToken.toString());   
        int t1 = parseTerm();
System.out.println("parseEXP:\tt1 = " + t1);   

        while (currentToken != null) {
            if( currentToken.equals("+") ) {
                currentToken = scanner.getNextToken();
System.out.println("parseEXP:\ttrovato + ! next token = " + this.currentToken.toString());   

                int t2 = parseTerm();
System.out.println("parseEXP:\tt2 = " + t2);   
                t1 = t1 + t2;
            }
            else if( currentToken.equals("-") ) {
                currentToken = scanner.getNextToken();
System.out.println("parseEXP:\ttrovato - ! nexto token = " + this.currentToken.toString());   

                int t2 = parseTerm();
System.out.println("parseEXP:\tt2 = " + t2);   

                t1 = t1 - t2;
            }
            else {
System.out.println("parseEXP:\ttrovato qualcosa che non riconosco");   
                return t1; // next token non fa parte di L(Exp)
            }
        } 

System.out.println("parseEXP:\tfiniti i token");   
        return t1; // next token nullo -> end input
    }

    public int parseTerm() {
System.out.println("parseTERM:\tcurrent token = " + this.currentToken.toString());   
        int f1 = parseFactor();
System.out.println("parseTERM:\tf1 = " + f1);   

        while (currentToken != null) {
            if( currentToken.equals("*") ) {
                currentToken = scanner.getNextToken();
System.out.println("parseTERM:\ttrovato * ! next token = " + this.currentToken.toString());   

                int f2 = parseFactor();
System.out.println("parseTERM:\tf2 = " + f2);   

                f1 = f1 * f2;
            }
            else if( currentToken.equals(":") ) {
                currentToken = scanner.getNextToken();
System.out.println("parseTERM:\ttrovato : ! next token = " + this.currentToken.toString());   

                int f2 = parseFactor();
System.out.println("parseTERM:\tf2 = " + f2);   
                
                f1 = f1 / f2;
            }
            else {
System.out.println("parseTERM:\ttrovato qualcosa che non riconosco");   

                return f1; // next token non fa parte di L(Term)
            }
        }

System.out.println("parseTERM:\tfiniti i token");   
        return f1; // next token nullo -> end input
    }

    public int parseFactor() {
System.out.println("parseFACTOR:\tcurrent token = " + this.currentToken.toString());   
        if (currentToken.equals("(")) {
            currentToken = scanner.getNextToken();
System.out.println("parseFACTOR:\ttrovata ( ! next token = " + this.currentToken.toString());   
            int innerExp = parseExp(); // self-embedding
System.out.println("parseFACTOR:\tinnerExp is " + innerExp);   

            if (currentToken.equals(")")) {
                currentToken = scanner.getNextToken();
if( currentToken != null)
System.out.println("parseFACTOR:\ttrovata ) ! next token = " + this.currentToken.toString());   
                return innerExp; // parentesi irrilevanti
            } 
            else {
System.out.println("parseFACTOR:\tmanca ) ! ritorno false");   
                throw new IllegalArgumentException("missing )");
            }
        }
        else { // dev’essere un numero
            if (currentToken.isNumber()) {
                int value = currentToken.getAsInt();
                currentToken = scanner.getNextToken();
if( currentToken != null)
System.out.println("parseFACTOR:\tnumero riconosciuto! next token = " + this.currentToken.toString());   

                return value;
            }
            else {// non è un fattore, quindi
System.out.println("parseFACTOR:\ttoken non riconosciuto!");   
                throw new IllegalArgumentException(currentToken + "not a number");
            }
        }
    }
}
