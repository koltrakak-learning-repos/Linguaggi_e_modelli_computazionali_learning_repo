/* 
    Il “solito” linguaggio L = { (^n c )^n, con n >= 0 }
    Alfabeto: A = { (, ), c }
    Regole: S → ( S ) | c
*/

import java.util.Scanner;

public class App {
    //global vars
    private static Scanner scanner = new Scanner(System.in);
    private static String FRASE;



    // private static char nextchar() {
    //     char nextchar = '\0';
    //     try {
            
    //         if (FRASE.length() == CUR_POS) {
    //             // ho già analizzato l'ultimo carattere e quindi inserisco a mano un fine stringa
    //             nextchar = '\0';
    //         }
    //         else {
    //             nextchar = FRASE.charAt(CUR_POS++);
    //         }
    //     } 
    //     catch (Exception e) {
    //         e.printStackTrace();
    //         System.exit(1);
    //     }

    //     return nextchar;
    // }

    private static String S(String cur_string) {
        switch(cur_string.charAt(0)){
            case 'c': 
                System.out.println("trovato c\n");
                return cur_string.substring(1);
            case '(':
                System.out.println("leggo '('" );
                cur_string = S(cur_string.substring(1));

                if ( cur_string !=null) {
                    if (cur_string.charAt(0) == ')') { 
                        System.out.println("trovata matching ')'\n");

                        return cur_string.substring(1);
                    }
                }
                else {
                    System.out.println("manca una parentesi chiusa ')'");
                    return null;
                }
            default:
                System.out.println("-> carattere non appartenente all'alfabeto {(, ), c}");
                return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.print("Inserisci una frase da riconoscere: ");
        FRASE = scanner.nextLine();

        String output = S(FRASE);
        if( output != null && output == "") 
            System.out.println("success!");
        else
            System.out.println("failure");
    }
}
