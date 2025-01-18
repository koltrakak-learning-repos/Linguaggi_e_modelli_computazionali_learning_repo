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

    public static void printStackTrace() {
        // Ottiene lo stack corrente
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        
        System.out.println("Stato dello stack:");
        for (StackTraceElement element : stackTrace) {
            System.out.println("\t" + element.getMethodName());
        }
    }

    private static char nextchar() {
        char nextchar = '\0';
        try {
            
            if (FRASE.length() == CUR_POS) {
                // ho già analizzato l'ultimo carattere e quindi inserisco a mano un fine stringa
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

    /*
     * Sincero, questa versione è più comprensibile, 
     * ogni livello fa la SUA lettura e non quella di qualcuno altro
     */
    private static boolean S() {
        char first;
        boolean result = false;

        // ogni livello si legge il suo carattere
        CUR_CHAR = nextchar();

        switch(CUR_CHAR){
            case 'c': 
                System.out.println("-> trovato c\n");
                printStackTrace();
                result = true;
                
                break;

            // questi due casi fanno la stessa cosa
            case '0':
            case '1': 
                System.out.println("->leggo " + CUR_CHAR);
                printStackTrace();
                first = CUR_CHAR; /* push */

                if ( S() ) { // verifico il sottolinguaggio
                    CUR_CHAR = nextchar(); // leggo il terminale a destra

                    if (CUR_CHAR==first) { /* pop */
                        System.out.println("->Confronto " + CUR_CHAR + " con " + first);
                        printStackTrace();
                        result = true;
                    }
                    else {
                        System.out.println("-> Confronto " + CUR_CHAR + " con " + first);
                        printStackTrace();
                        result = false;
                    }
                }
                else {
                    System.out.println("Sottolinguaggio sbagliato");
                    result = false;
                }

                break;
            default:
                System.out.println("-> carattere non appartenente all'alfabeto {0, 1, c}");
                printStackTrace();
                result = false;
                break;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.print("Inserisci una frase da riconoscere: ");
        FRASE = scanner.nextLine();

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
