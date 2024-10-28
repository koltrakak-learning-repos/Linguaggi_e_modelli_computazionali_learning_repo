import exp.*;

public class TestParser {
    public static void main(String args[]) {
        /* TODO: sostituisci questo array con un file */
        String[] expressions = {
            "12 + 4 - 11 ",
            "12 + 4 - 14 : 2 ",
            "3 + 4 * 5",
            "( 3 + 4 ) * 5 ",
            "x = 5 - 3",
            "y = 4 + $ x",
            "y = -4 * $ y",
            "z = x = $ y"
        };

        String expression = expressions[6];
        MyScanner scanner = new MyScanner(expression);
        MyParser parser = new MyParser(scanner);
        Exp ast = parser.parseExp();

        System.out.println(ast); // da valutare poi
    }
}