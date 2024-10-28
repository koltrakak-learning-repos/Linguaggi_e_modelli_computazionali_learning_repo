import visitor.*;
import exp.*;

public class App {
    public static void main(String[] args) throws Exception {
        String[] expressions = {
            "12 + 4 - 11 ",
            "12 + 4 - 14 / 2 ",
            "3 + 4 * 5",
            "( 3 + 4 ) * 5 ",
            "x = 5 - 3",
            "y = 4 + $ x",
            "y = -4 * $ y",
            "z = x = $ y"
        };

        ParseExpVisitor parseVisitor = new ParseExpVisitor();
        EvalExpAssignVisitor evalVisitor = new EvalExpAssignVisitor();

        for (String exp : expressions) {
            MyScanner scanner = new MyScanner(exp);
            MyParser parser = new MyParser(scanner);
            Exp ast = parser.parseExp();
            
            ast.accept(parseVisitor);
            ast.accept(evalVisitor);

            System.out.print(ast + " -> " + parseVisitor.getResult() + " -> " + evalVisitor.getEvaluation());
            System.out.println("\t" + evalVisitor.getEnv());
        }
    }
}
