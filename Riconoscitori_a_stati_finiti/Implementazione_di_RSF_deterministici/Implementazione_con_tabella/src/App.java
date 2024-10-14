import java.util.Scanner;

public class App {
    enum Stato {SI, S1, SF, SE};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char alfabeto[] = {'a', 'b'};

        Stato tabellaTransizioni[/* stato */][/* char input */] = {
            {Stato.S1,Stato.SE},    // SI
            {Stato.S1,Stato.SF},    // S1
            {Stato.SE,Stato.SE},    // SF
            {Stato.SE,Stato.SE},    // SE
        };
        
        Stato curStato = Stato.SI;
        int curPos = 0;
        boolean termina = false;
        
        System.out.print("Inserisci la stringa da riconoscere: ");
        String frase = scanner.nextLine();

        while (curPos < frase.length() && !termina) {
            char curChar = frase.charAt(curPos++);

            System.out.println("\tSono nello stato " + curStato);
            System.out.println("\tAnalizzo il carattere: " + curChar);

            int pos = java.util.Arrays.binarySearch(alfabeto, curChar);

            if (pos < 0) {
                System.out.println("\t\tCarattere" + curChar + " NON presente nell'alfabeto!");
                termina = true;
            }
            else {
                System.out.println("\t\ttransito da " + curStato + " a " + tabellaTransizioni[curStato.ordinal()][pos]);
                curStato = tabellaTransizioni[curStato.ordinal()][pos];
            }

            if ( curStato == Stato.SE) {
                System.out.println("\t\tSono entrato in uno stato di errore!");
                termina = true;
            }
        }

        scanner.close();

        if (termina != true && curStato == Stato.SF) 
            System.out.println("Stringa riconosciuta");
        else
            System.out.println("Stringa non riconosciuta");
    }
}
