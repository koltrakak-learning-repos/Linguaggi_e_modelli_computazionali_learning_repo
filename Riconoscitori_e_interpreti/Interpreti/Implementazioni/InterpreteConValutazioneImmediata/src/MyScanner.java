import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyScanner {
    private final String generic_token_reg_exp = "\\d+|\\+|-|\\*|\\/|\\^|\\(|\\)|=|;|,|:|[a-zA-Z_]\\w*";

    private String testo;
    private Pattern pattern;
    private Matcher matcher;
    

    public MyScanner(String testo){
        this.testo = testo;
        this.pattern = Pattern.compile(generic_token_reg_exp);
        this.matcher = pattern.matcher(this.testo);
    }

    public Token getNextToken(){
        boolean matchFound = matcher.find();
        
        if(matchFound) {
            // System.out.println("\t\tMatch found: " + matcher.group()); 
            // System.out.println("\t\tindice inizio del match: " + matcher.start()); 
            // System.out.println("\t\tindice fine del match: " + matcher.end());

            return new Token(matcher.group());
        } else {
            // System.out.println("\t\tMatch not found");

            return null;
        }
    }
 }