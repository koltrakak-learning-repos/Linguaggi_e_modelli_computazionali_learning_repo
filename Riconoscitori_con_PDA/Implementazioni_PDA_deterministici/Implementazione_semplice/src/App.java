/* 
    Il “solito” linguaggio L = { word c word_R }
    Alfabeto: A = { 0, 1, c }
    Regole: S → 0 S 0 | 1 S 1 | c
*/

import java.util.Scanner;

public class App {
    //global vars
    private static Scanner scanner = new Scanner(System.in);
    private static String FRASE;
    private static char CUR_CHAR;
    private static int CUR_POS = 0;

    private static char nextchar() {
        char nextchar = '\0';
        try {
            if (FRASE.length() == CUR_POS) {
                //ho già analizzato l'ultimo carattere
                nextchar = '\0';
            }
            else {
                nextchar = FRASE.charAt(CUR_POS++);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return nextchar;
    }

    private static boolean S() {
        char first;
        boolean result = false;

        switch(CUR_CHAR){
            case 'c': 
                System.out.println("\ttrovato c");

                CUR_CHAR = nextchar();
                result = true;
                break;
            case '0':
            case '1': 
                System.out.println("\tleggo " + CUR_CHAR);
                first = CUR_CHAR; /* push */

                CUR_CHAR = nextchar();  //per il prossimo livello

                if (S() && CUR_CHAR==first) { /* pop */
                    System.out.println("\tConfronto " + CUR_CHAR + " con " + first);
                    CUR_CHAR = nextchar();
                    result = true;
                }
                else {
                    result = false;
                }
                break;
            default:
                result = false;
                break;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.print("Inserisci una frase da riconoscere: ");
        FRASE = scanner.nextLine();

        CUR_CHAR = nextchar();

        /*
        Qui nessuno verifica cosa ci sia in ch al
        ritorno di S() → si accettano anche frasi con
        caratteri "spuri" in coda a una frase corretta
        */
        if( S() ) 
            System.out.println("success!");
        else
            System.out.println("failure");

        // qua abbiamo gia il prossimo carattere pronto da dare al prossimo pezzo del riconoscitore
        //System.out.println(CUR_CHAR);
    }
}
