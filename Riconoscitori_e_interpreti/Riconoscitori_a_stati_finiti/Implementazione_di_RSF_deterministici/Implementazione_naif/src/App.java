import java.util.Scanner;

public class App {
    enum Stato {SI, S1, SF};
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        
        Stato stato = Stato.SI;
        int curPos = 0;
        boolean termina = false;
        
        String frase = scanner.nextLine();

        while(curPos < frase.length() && !termina) {
            char currentChar = frase.charAt(curPos++);

            switch (stato) {
                case Stato.SI: 
                    if (currentChar=='a')
                        stato=Stato.S1;
                    else
                        termina=true;
                    break;
                case Stato.S1: 
                    if (currentChar=='a')
                        stato=Stato.S1;
                    else if (currentChar=='b')
                        stato=Stato.SF;
                    else
                        termina = true; 
                    break;
                case Stato.SF:
                    termina = true;
            }
        }

        scanner.close();

        if (termina != true) 
            System.out.println("Stringa riconosciuta");
        else
            System.out.println("Stringa non riconosciuta");
    }
}
