import visitor.*;
import exp.*;

public class App {
    public static void main(String[] args) throws Exception {
        String expression1 = "2 ^ 2 ^ 3";
        String expression2 = "5 - 4 - 3";
        String expression3 = " 3 + 4  /  2 - 1 ";
        String expression4 = "( 3 + 4 ) ^ 2 / 2 - 1 ^ 2";

        ParseExpVisitor v = new ParseExpVisitor();
        EvalExpVisitor v2 = new EvalExpVisitor();
        BytecodeVisitor v3 = new BytecodeVisitor("./compilato.code");

        MyScanner scanner = new MyScanner(expression1);
        MyParser parser = new MyParser(scanner);
        Exp ast1 = parser.parseExp();
        ast1.accept(v);
        System.out.println(ast1 + " = " + v.getResult() + "\n");
        
        scanner = new MyScanner(expression2);
        parser = new MyParser(scanner);
        Exp ast2 = parser.parseExp();
        ast2.accept(v);
        System.out.println(ast2 + " = " + v.getResult() + "\n");

        scanner = new MyScanner(expression3);
        parser = new MyParser(scanner);
        Exp ast3 = parser.parseExp();
        ast3.accept(v2);
        System.out.println(ast3 + " = " + v2.getResult() + "\n");

        scanner = new MyScanner(expression4);
        parser = new MyParser(scanner);
        Exp ast4 = parser.parseExp();
        ast4.accept(v3);
        System.out.println(ast4 + " = " + v2.getResult() + "\n");
    }
}
