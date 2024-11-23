- Il concetto di funzioni di ordine superiore serve risparmiare codice e ad avere una maniera agevole di definire delle funzioni di callback? Oppure permette di fare qualcosa che non si può fare senza?

- Che differenza c'è tra strongly e weakly/loosely (e dynamically e statically) typed languages?

- Come funziona adesso la chiamata di funzione? c'è ancora il record di attivazione? i riferimenti all'heap per le variabili libere della chiusura come sono fatti?

- Come fa ad un interprete a capire dove allocare le variabili che forse sono libere o forse no in una funzione più interna?



### Programma

int a = 10  -> environment globale  -> salvato su una mappa del visitor che valuta gli assegnamenti globali? 

int main() {    -> nuova funzione -> il visitor che valuta le funzioni crea un nuovo frame in uno stack che salva gli environmente delle funzioni
    int b = 11 -> salvato nella entry corrispondente nello stack

    {
        int c = 12  -> visitor che valuta i blocchi aggiunge una entry in un suo stack che però contiene anche riferimenti alla variabili della funzione
    }
}

function ff() {
  var a = 13;
    var f = () => a+10
    
    return f
}

• Intenzionalmente, non esiste un analogo in kotlin
• esiste però la possibilità di valutare proprietà in modo lazy,
intendendo che vengano valutate al primo utilizzo: ma non è la
stessa cosa, né ha la stessa potenza espressiva