Come si fa se si vuole esprimere un problema utilizzando la tail recursion, ma il linguaggio utilizzato non supporta la TRO? 
(In tal caso, le soluzioni ricorsive occupano spazio di stack → rischio di stack overflow al crescere delle «iterazioni». Superando un certo limite,
lo spazio di stack si esaurirà, e allora… BOOM!)

Soluzione: o si trasforma l’algoritmo in una iterazione :(, oppure si implementa la TRO a mano!

Per costruirsi a mano tale supporto, la tecnica più nota è quella del **trampolino**
- IDEA CHIAVE: per non riempire lo stack, prima di effettuare la chiamata ricorsiva **occorre terminare la chiamata precedente** per liberare lo stack frame
- PER FARLO, occorre un qualche «ausilio esterno» che «scateni la prossima chiamata», ma non prima che la precedente sia terminata 
    - il «trampolino», appunto
- COSA SERVE: un approccio lazy (call-by-name) + opportuna sovrastruttura

- IDEA CHIAVE 2: la funzione tail-ricorsiva non richiama direttamente se stessa, ma opera in modo  lazy

### Procedimento:
1. La funzione tail-ricorsiva, anziché richiamarsi direttamente, **restituisce una lambda che effettuerà tale chiamata «in futuro»** → così, lo stack non cresce, perché la funzione termina subito

    function fact(n, acc=1) {
        if (n === 0)
            return acc;
        else
            return () => fact(n-1, n*acc);
    }

2. Il trampolino rilancerà l’esecuzione iterativamente, finché la funzione non restituirà il risultato

    function trampoline(f) {
        while (f && f instanceof Function) {
            f = f();
        }

        return f; // in questo caso non è più una funzione
    }

**OSS**: Da notare che il trampolino così implementato è assolutamente generale, usabile con qualsiasi funzione.

**ATTENZIONE**: il tipo restituito è diverso nei due casi. Ciò ben si adatta a un linguaggio loosely-typed, mentre richiede notevoli adattamenti in linguaggi strongly-typed.

Due casi:
1. Linguaggio loosely typed (ad esempio JS):
    - è facile applicare la tecnica del trampolino direttamente dato che la funzione tail ricorsiva può resiture il risultato oppure l'oggetto funzione che rappresenta la prossima iterazione, senza problemi di tipo.
2. Linguaggio strongly typed
    - In linguaggi strongly-typed, il tipo di ritorno non può cambiare impunemente da «funzione» a «valore»
    - Ergo, occorre inventarsi un’opportuna architettura software che mascheri questi aspetti e sia **type-compliant**
    - Idea di fondo:
        - Separare esplicitamente il caso «computazione che continua» dal caso «computazione che termina» in entità linguistiche distinte
        - Per farlo, o si prevedono due classi (es. More / Done) o due metodi (more /done), opportunamente connessi nell’architettura. Più, naturalmente, classi e metodi di supporto secondo necessità
    - In poche parole che palle

**NB**: La TRO con trampolino può essere utile anche in linguaggi come scala e kotlin implementa già la TRO, ma solo nel caso di ricorsione diretta. 

**NB_2**: Questo caso d'uso della TRO, mostra come con un linguaggio con dei tipi loose come JS, si possano risparmiare un sacco di righe di codice.