public class App {
    public static void main(String[] args) throws Exception {
        String expression1 = "2 ^ 2 ^ 3";
        String expression2 = "5 - 4 - 3";
        String expression3 = "( 3 + 4 ) : ( 2 - 1 )";
        String expression4 = "( 3 + 4 ) ^ 2 : ( 2 - 1 ) ^ 2";

        MyScanner scanner = new MyScanner(expression1);
        MyParser parser = new MyParser(scanner);
        int result = parser.parseExp();
        System.out.println(expression1 + " = " + result + "\n");

        scanner = new MyScanner(expression2);
        parser = new MyParser(scanner);
        result = parser.parseExp();
        System.out.println(expression2 + " = " + result + "\n");

        scanner = new MyScanner(expression3);
        parser = new MyParser(scanner);
        result = parser.parseExp();
        System.out.println(expression3 + " = " + result + "\n");

        scanner = new MyScanner(expression4);
        parser = new MyParser(scanner);
        result = parser.parseExp();
        System.out.println(expression4 + " = " + result + "\n");
    }
}
