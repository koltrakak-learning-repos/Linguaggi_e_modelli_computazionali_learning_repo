### INTERPRETAZIONE, COMPILAZIONE e COMPILAZIONE JIT

Abbiamo visto che un interprete:
- accetta una frase alla volta
- produce l'AST
- valuta l'AST in modo tale da eseguire la semantica della frase di partenza

Un compilatore invece
- accetta un intero programma (più frasi)
- Produce vari AST (**?**)
- valuta  gli l'AST in ordine per riscrivere la frase di partenza in una (o più) frasi in un altro linguaggio (più semplice).
    - da fare eseguire direttamente alla macchina
    - oppure da far sottoporre ad altri passaggi di interpretazione/compilazione

La differenza è sottile, un programma potrebbe essere considerato una frase lunga con dei newline. In generale, un interprete produce direttamente il
risultato finale "che ci si aspetta". Un compilatore produce un passaggio intermedio (ad esempio codice assembly). La differenza tuttavia, è nell'occhio di chi osserva, se ci si apetta dell'assembly, anche un compilatore potrebbe essere considerato un interprete. 

### Come devo pensare alla Compilazione JIT??