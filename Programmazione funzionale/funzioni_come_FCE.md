## Funzioni come first class entities
Tutti i linguaggi di programmazione offrono costrutti per esprimere funzioni, ma spesso non come **first-class entities**

Una funzione che sia first-class entity deve essere **manipolabile come ogni altro tipo di dato** e quindi:
- deve poter essere **assegnata a variabili** (di un tipo "funzione")
- deve poter essere **passata come argomento** a un'altra funzione
- deve poter essere **restituita** da un'altra funzione
- deve poter essere **definita e usata "al volo"** come ogni altro valore (literal) di ogni altro tipo
    - magari anche senza avere per forza un nome, (funzioni anonime/lambda expression)

**NB**: 
- **Si elimina la separazione (arbitraria) tra codice e dati**
- **il codice diventa un nuovo tipo di dato** analogo, a quelli già esistenti; con, in più, la caratteristica di poter venire eseguito.

Nei linguaggi imperativi tradizionali, invece, una funzione tipicamente è solo un costrutto che incapsula codice.
- approssimazione | i **puntatori a funzione del C**: essi rispettano alcune delle caratteristiche elencate sopra, ma non sono una vera "entità funzione", non denotano un vero nuovo tipo di dato vero e proprio. Puntano solo ad un indirizzo di memoria (executable) contenente del codice.

Disporre di funzioni come FCE apre le porte a molti altri concetti:
- **funzioni di ordine superiore**
    - funzioni di secondo ordine manipolano (accettano/ritornano) funzioni 
    - funzioni di terzo ordine manipolano funzioni di terzo ordine
    - ecc...
- **Chiusure**
- Currying
- **Lazyness**

### Funzioni come tipi
// function declaration;
// prima le funzioni davano semplicemente un nome a del codice
function sum(a,b){
    return a+b
}

// function expression;
// La funzione diventa un (tipo di) dato: si può «portarla in giro» come valore e valutarla come espressione
var f = function(a,b){
    return a+b
}

In particolare, una questione da definire è: siccome le funzioni diventano passabili come argomento e restituibili, e sono analoghe a qualsiasi altro tipo di dato
- **che tipo hanno le funzioni?**
- **Hanno tutte lo stesso tipo**?

Avrebbe senso distingure in tipi-funzione diversi funzioni che restituiscono, oppure hanno argomenti in numero e tipo diverso.
- se il **linguaggio è loosely-typed** (e.g. Javascript) l'idea di tipo non è stringente e quindi **basta un generico tipo «function»**, non ulteriormente caratterizzato
    - le funzioni sono allora tutte solo dei tipi «function», **intercambiabili fra loro**
    - non si distinguono perciò «tipi» di funzione diversi, né in base alla lista degli argomenti né al tipo di ritorno
- se invece il **linguaggio è strongly-typed**, occorre distinguere lo specifico **tipo di funzione in base a dominio e codominio**, ossia
    - lista di argomenti (quanti e di che tipo)
    - tipo del valore restituito
    - a questo punto **funzioni di tipo diverso diventano incompatibili tra loro** come lo sono normali dati di tipo diverso
        - Non si può fare un assegnamento di una funzione ad un'altra di tipo diverso
        - Una funzione non accetta una funzione di tipo che non si aspetta come argomento
        - ecc...

In particolare, nel caso di linguaggi strongly-typed, si hanno **due approcci nel definire un tipo-funzione**:
- **Strutturale**: specifica come è fatta la funzione in termini di lista degli argomenti accettati e tipo di ritorno
    - esempi: «funzione da int a int», «funzione da double,double a int»
    - questo approccio è **scalabile** e permette di risparmiare nomi per i vari tipi di funzione
- **Nominale**: da un nome a quel tipo di funzione
    - vince la semantica; se due cose hanno nomi diversi, allora sono diverse, in quanto il nome distinto comunica l'intento di non voler raggruppare nello stesso tipo queste due funzioni,  **anche se hanno la stessa struttura**
    - esempi: «IntUnaryOperator», «Function<Integer, Integer>»; 
        - notare come passare la seconda ad una funzione che si aspetta la prima non andrebbe bene, anche se in realtà entrambi descrivono una funzione che accetta un int e restituisce un int 
    - pro: distinzione semantica
    - contro: producono un puttanaio di nomi


#### Funzioni di ordine più alto
...
si privilegia la facilità del chiamante rispetto a quello del chiamato