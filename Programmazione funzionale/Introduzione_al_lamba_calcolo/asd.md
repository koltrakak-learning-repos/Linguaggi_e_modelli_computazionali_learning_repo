situazione simile come a quella della macchina di turing. Con quest'ultima si è capito che si può esprimere qualunque algoritmo (di problemi risolvibili), ma scrivere un algoritmo con la MdT è una follia. 

Il lamba calcolo vuole descrivere qualunque algoritmo mediante solamente il concetto di funzione(e chiaramente di variabile argomento). Permette di capire che cosa si riesce a fare e pone le basi per tutti i linguaggi funzionali.


### Prima slide
...

Interessante, qua non abbiamo il concetto di costante. L'unica cosa costante sono le dichiarazioni di funzioni, e quindi si utilizzano anche quelle per definire funzioni. Ad esempio: () -> 3

...

**OSSERVAZIONE EPICA**: Il Lamba Calcolo computa tramite **RICERCA E SOSTITUZIONE DI TESTO**. Montando funzioni su funzioni Church ha dimostrato che questi strumenti formano un sistema Turing-equivalente.



### Lamba calcolo nei linguaggi mainstream

**CURIOSITà**: Nei linguaggi moderni le lambda expression si chiamano in questo modo perchè derivano da questa teoria.

...

Anche qua, attenzione ai tipi! ...

...

**Prima lezione del lamba calcolo**: Per invocare una funzione, darle un nome non è strettamente necessario!

...

### Definizione della semantica

... concetto di riduzione ...

ESEMPIO CONTORTO: A volte è utile pensare agli argomenti come stringhe nei primi passaggi. E poi vedere se sono altre funzioni e come applicarle nei passaggi successivi...

...

### Currying
Abbiamo solo funzioni ad un argomento (mattone elementare) ma non siamo limitati. 

Possiamo definire una funzione che chiude una funzione di ordine inferiore che mangia il secondo argomento che a sua volta può chiudere un'altra funzione per il terzo argomento ecc...

Chiaramente è più comodo appiattire i livelli e avere le funzioni a più argomenti, ma il fondamento è questo.

STIAMO PERDENDO QUALCOSA??? CENTRANO QUALCOSA LE FUNZIONI CON UN NUMERO DI ARGOMENTI VARIABILE???
PERCHè JS ha queste cose???

...

### La computazione del lamba calcolo termina o no?

Anche qua possiamo andare in loop (d'altronde siamo turing equivalenti); -> Funzione notevole W

...

debolmente normalizzabile

...

...

10 slide di sofferenza in Java: vabbè, qua voleva proprio lamentarsi... che grande
**IMPORTANTE**: Bisogna sapere come mai è problematico cercare di rappresentare questa cosa con un linguaggio tipato fortemente!


### Strategie di riduzione
...
**IMPORTANTE**: Le famiglie di call by name e call by value, nascono da come si sceglie l'ordine di valutazione di una espressione nel lambda calcolo  