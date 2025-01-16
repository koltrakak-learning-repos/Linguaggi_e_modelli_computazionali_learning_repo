import visitor.*;
import exp.*;

public class App {
    public static void main(String[] args) throws Exception {
        /* TODO: sostituisci questo array con un file e leggi linea per linea */
        String[] expressions = {
            "12 + 4 - 11 ",
            "12 + 4 - 14 / 2 ",
            "3 + 4 * 5",
            "( 3 + 4 ) * 5 ",
            "x = 5 - 3",
            "y = 4 + $ x",
            "y = -4 * $ y",
            "z = x = $ y",
            "x = 5 , y = $ x",
            "y = 4 + $ x , 3 - 5",
            "x = 7 , y = 4 + $ x",
            "x = 7 , y = 4 + $ x , w = $ y + 1",
            "( x = 7 , y = 4 + $ x ) , w = $ y + 1",
            "x = 7 , ( y = 4 + $ x , w = $ y + 1 )"
        };

        ParseExpVisitor parseVisitor = new ParseExpVisitor();
        EvalSeqExpVisitor evalVisitor = new EvalSeqExpVisitor();

        for (String exp : expressions) {
            MyScanner scanner = new MyScanner(exp);
            MyParser parser = new MyParser(scanner);
            Exp ast = parser.parseSeq();
            
            ast.accept(parseVisitor);
            ast.accept(evalVisitor);

            System.out.print(ast + " -> " + parseVisitor.getResult() + " -> " + evalVisitor.getEvaluation());
            System.out.println("\t" + evalVisitor.getEnv());
        }
    }
}
