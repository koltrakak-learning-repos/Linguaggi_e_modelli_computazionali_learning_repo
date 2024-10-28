import exp.*;

public class TestParser {
    public static void main(String args[]) {
        String[] expressions = {
            "z = x = $ y",
            "x = 5 , y = $ x",
            "y = 4 + $ x , 3 - 5",
            "x = 7 , y = 4 + $ x",
            "x = 7 , y = 4 + $ x , w = $ y + 1",
            "( x = 7 , y = 4 + $ x ) , w = $ y + 1",
            "x = 7 , ( y = 4 + $ x , w = $ y + 1 )"
        };

        for(String exp : expressions) {
            MyScanner scanner = new MyScanner(exp);
            MyParser parser = new MyParser(scanner);

            Exp ast = parser.parseSeq(); // NUOVO SCOPO GRAMMATICA
            System.out.println(ast); // da valutare poi
        }
    }
}