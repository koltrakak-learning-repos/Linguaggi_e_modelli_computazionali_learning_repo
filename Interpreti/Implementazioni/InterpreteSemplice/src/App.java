public class App {
    public static void main(String[] args) throws Exception {
        String expression = "3 - 4 - 5";

        MyScanner scanner = new MyScanner(expression);
        MyParser parser = new MyParser(scanner);

        int result = parser.parseExp();
        System.out.println(expression + " = " + result);
    }
}
