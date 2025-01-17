import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static boolean next_match(Matcher matcher) {
        boolean matchFound = matcher.find();
      
        if(matchFound) {
            System.out.println("Match found: " + matcher.group()); 
            System.out.println("\tindice inizio del match: " + matcher.start()); 
            System.out.println("\tindice fine del match: " + matcher.end());

            return true;
        } else {
            System.out.println("Match not found");

            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        // \d+	| digits
        // \+ 	|
        // -  	|
        // \* 	|
        // /  	|
        // \( 	|
        // \)	|
        // =	|
        // ;	|
        // ,	|
        // :	|
        // [a-zA-Z_]\w*; con \w === [a-zA-Z0-9_]

        // java ha bisogno del doppio escape \\
        Pattern pattern = Pattern.compile("\\d+|\\+|-|\\*|\\/|\\(|\\)|=|;|,|:|[a-zA-Z_]\\w*");
        // Pattern pattern = Pattern.compile("[a-zA-Z_]\\w*"); // per solo identificatori
        Matcher matcher = pattern.matcher("_a123f =     1 + 1 ;");
        
        while(next_match(matcher)) {}

    }
}
