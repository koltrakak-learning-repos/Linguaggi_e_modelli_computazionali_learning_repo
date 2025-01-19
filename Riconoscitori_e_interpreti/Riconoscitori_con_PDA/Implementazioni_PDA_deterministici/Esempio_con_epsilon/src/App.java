/* 
    Quasi i numeri naturali
    N->CD
    D->e|N          // epsilon
    C->0|1|2… 9

    Linguaggio regolare
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

    private static boolean N() {
        boolean result = false;
        System.out.println("N: leggo " + CUR_CHAR);

        switch(CUR_CHAR){
            // questi fanno la stessa cosa
            case '0':
            case '1': 
            case '2':
            case '3': 
            case '4':
            case '5': 
            case '6':
            case '7': 
            case '8':
            case '9': 
                printStackTrace();

                // NB: per C non devo leggere il suo carattere

                if (C() && D()) { 
                    printStackTrace();

                    CUR_CHAR = nextchar();
                    result = true;
                }
                else {
                    printStackTrace();

                    result = false;
                }
                break;

            default:
                System.out.println("-> carattere non appartenente all'alfabeto {0, 1, ... , 9}");
                printStackTrace();
                result = false;
                break;
        }

        System.out.println("N: ritorno" + result);
        return result;
    }

    private static boolean C() {
        boolean result = false;
        System.out.println("C: leggo " + CUR_CHAR);

        switch(CUR_CHAR){
            // questi fanno la stessa cosa
            case '0':
            case '1': 
            case '2':
            case '3': 
            case '4':
            case '5': 
            case '6':
            case '7': 
            case '8':
            case '9': 
                printStackTrace();
                CUR_CHAR = nextchar();

                result = true;
                break;

            default:
                System.out.println("-> carattere non appartenente all'alfabeto {0, 1, ... , 9}");
                printStackTrace();
                result = false;
                break;
        }

        System.out.println("C: ritorno" + result);
        return result;
    }

    private static boolean D() {
        boolean result = false;
        System.out.println("D: leggo " + CUR_CHAR);

        switch(CUR_CHAR){
            // questi fanno la stessa cosa
            case '0':
            case '1': 
            case '2':
            case '3': 
            case '4':
            case '5': 
            case '6':
            case '7': 
            case '8':
            case '9': 
                if (N()) { 
                    printStackTrace();

                    CUR_CHAR = nextchar();
                    result = true;
                }
                else {
                    printStackTrace();
                    result = false;
                }
                break;

            case '\0':
                // epsilon rule, non devo fare niente. Neanche consumare l'input
                System.out.println("D: applico la epsilon-rule");
                printStackTrace();
                result = true;
                break;
                
            default:
                printStackTrace();
                result = false;
                break;
        }

        System.out.println("D: ritorno" + result);
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
        if( N() ) 
            System.out.println("success!");
        else
            System.out.println("failure");

        // qua abbiamo gia il prossimo carattere pronto da dare al prossimo pezzo del riconoscitore
        //System.out.println(CUR_CHAR);
    }
}
