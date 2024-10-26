import java.io.PrintWriter;
import java.io.File;


import exp.*;

public class App {
    public static void main(String[] args) throws Exception {
        String expression1 = "2 ^ 2 ^ 3";
        String expression2 = "5 - 4 - 3";
        String expression3 = "( 3 + 4 ) / ( 2 - 1 )";
        String expression4 = "( 3 + 4 ) ^ 2 / 2 - 1 ^ 2";

        MyScanner scanner = new MyScanner(expression1);
        MyParser parser = new MyParser(scanner);
        Exp ast1 = parser.parseExp();
        System.out.println(expression1 + " = " + ast1 + " = " + ast1.eval() +"\n");

        scanner = new MyScanner(expression2);
        parser = new MyParser(scanner);
        Exp ast2 = parser.parseExp();
        System.out.println(expression2 + " = " + ast2 + " = " + ast2.eval() + "\n");

        scanner = new MyScanner(expression3);
        parser = new MyParser(scanner);
        Exp ast3 = parser.parseExp();
        System.out.println(expression3 + " = " + ast3 + " = " + ast3.eval() + "\n");

        scanner = new MyScanner(expression4);
        parser = new MyParser(scanner);
        Exp ast4 = parser.parseExp();
        System.out.println(expression4 + " = " + ast4 + " = " + ast4.eval() + "\n");

        PrintWriter p = new PrintWriter(new File("./Interpreti/Implementazioni/Interprete_AST_con_valutazione/compilato.code"));
        ast4.emit(p);
        p.close();
    }
}
