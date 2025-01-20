import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // String expression1 = "2 ^ 2 ^ 3";
        // String expression2 = "5 - 4 - 3";
        // String expression3 = "( 3 + 4 ) : ( 2 - 1 )";
        // String expression4 = "( 3 + 4 ) ^ 2 : ( 2 - 1 ) ^ 2";
        Scanner userInput = new Scanner(System.in);
        MyScanner scanner;
        MyParser parser;
        String exp;
        int result;
        
        System.out.print("inserisci una espressione: ");
        while( userInput.hasNextLine() ) {
            exp=userInput.nextLine();
            scanner = new MyScanner(exp);
            parser = new MyParser(scanner);

            result = parser.parseExp();
            System.out.println(exp + " = " + result + "\n");

            System.out.print("inserisci una espressione: ");
        }

        System.out.println("Fine");
        userInput.close();
    }
}
