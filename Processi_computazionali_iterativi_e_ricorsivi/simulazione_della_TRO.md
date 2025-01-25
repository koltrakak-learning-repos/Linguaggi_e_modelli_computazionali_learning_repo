Come si fa se si vuole esprimere un problema utilizzando la tail recursion, ma il linguaggio utilizzato non supporta la TRO? 
- In tal caso, le chiamate ricorsive occupano spazio di stack e superato un certo limite lo stack overflow è una certezza

Soluzione:
- o si trasforma l’algoritmo in una iterazione :(
- oppure si implementa la TRO a mano!



## TRO con TRAMPOLINO
Per costruirsi a mano tale supporto, la tecnica più nota è quella del **trampolino**
- **IDEA CHIAVE**: per non riempire lo stack, **prima di effettuare la chiamata ricorsiva, occorre terminare la chiamata precedente** per liberare lo stack frame
- per far questo, occorre un qualche «ausilio esterno» che «scateni la prossima chiamata», ma non prima che la precedente sia terminata (il trampolino)
- **COSA SERVE**: 
    - **un approccio lazy** 
    - opportuna sovrastruttura (solo una funzione nel caso di JS; architettura di classi interfacce negli altri linguaggi)

Il Trampolino è un’astrazione che **governa un’iterazione «camuffata» esternamente da ricorsione**
- **IDEA CHIAVE 2**: la funzione tail-ricorsiva **NON richiama direttamente se stessa (subito)**, ma opera in modo **lazy**


### Procedimento:
1. La funzione tail-ricorsiva, anziché richiamarsi direttamente, **restituisce una lambda che effettuerà tale chiamata «in futuro»** → così, lo stack non cresce, perché la funzione termina subito

    function fact(n, acc=1) {               // la funzione ha comunque la "faccia" ricorsiva se non per la lambda 
        if (n === 0)
            return acc;                     // ritorno il valore finale
        else
            return () => fact(n-1, n*acc);  // non ritorno il risultato, ritorno la prossima iterazione!
    }

2. Il trampolino rilancerà l’esecuzione iterativamente, finché la funzione non restituirà il risultato

    function trampoline(f) {
        while (f && f instanceof Function) {    // finchè non si è ottenuto il risultato finale
            f = f();
        }

        return f; // in questo caso non è più una funzione
    }

3. uso: *console.log( trampoline( fact(18000) )) *

**OSS**: Da notare che il trampolino così implementato è assolutamente generale, usabile con qualsiasi funzione.

**ATTENZIONE**: **il tipo restituito è DIVERSO nei due casi**. Ciò ben si adatta a un linguaggio loosely-typed, mentre **richiede notevoli adattamenti in linguaggi strongly-typed**.

**Due casi**:
1. Linguaggio loosely typed (ad esempio JS):
    - è facile applicare la tecnica del trampolino direttamente dato che la funzione tail ricorsiva può resiture sia il risultato che l'oggetto funzione che rappresenta la prossima iterazione, senza problemi di tipo.
2. Linguaggio strongly typed
    - In linguaggi strongly-typed, il tipo di ritorno non può cambiare impunemente da «funzione» a «valore»
    - Ergo, occorre inventarsi un’opportuna architettura software che mascheri questi aspetti e sia **type-compliant**
    - Idea di fondo:
        - **Separare** esplicitamente il **caso «computazione che continua»** dal **caso «computazione che termina»** in entità linguistiche distinte
        - Per farlo, o si prevedono **due classi (es. More / Done)** o **due metodi (more /done)**, opportunamente connessi nell’architettura. 
        - Più, naturalmente, classi e metodi di supporto secondo necessità
    - Una possibilità è definire un'interfaccia *trampolinable* ed implementarla con due classi *More* e *Done*
        - a questo punto la funzione tail-ricorsiva restituirà o More o Done (sempre Trampolinable) e sarà il trampolino a dover invocare i giusti metodi
    - In poche parole, che palle


**NB**: La TRO con trampolino può essere utile anche in linguaggi come scala e kotlin implementa già la TRO, ma solo nel caso di ricorsione diretta. 

**NB_2**: Questo caso d'uso della TRO, mostra come con un linguaggio con dei tipi loose come JS, si possano risparmiare un sacco di righe di codice.