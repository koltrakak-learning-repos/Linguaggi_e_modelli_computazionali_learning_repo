Situazione simile come a quella della macchina di turing. Con quest'ultima si è capito che si può computare qualunque algoritmo (di problemi risolvibili), ma scrivere un algoritmo con la MdT è una follia. 

Il lamba calcolo vuole descrivere qualunque algoritmo mediante solamente il concetto di funzione (e chiaramente di variabile argomento). Permette di capire che cosa si riesce a fare e pone le basi per tutti i linguaggi funzionali.

### Prima slide
...

Interessante, qua non abbiamo il concetto di costante. L'unica cosa costante sono le dichiarazioni di funzioni, e quindi si utilizzano anche quelle per definire funzioni. Ad esempio: () -> 3

**OSSERVAZIONE EPICA**: Il Lamba Calcolo computa tramite **RICERCA E SOSTITUZIONE DI TESTO**. Montando funzioni su funzioni Church ha dimostrato che questi strumenti formano un sistema Turing-equivalente.

### Lambda termini
Un lambda-termine può essere:
- **x**, una variabile ('*lambda*' e '.' sono simboli terminali)
- ***lambda*_x.L**, una funzione
    - senza nome (chiamata chiusura)
    - con un parametro x (una variabile)
    - e un corpo L, che è a sua volta un lambda-termine
- **LM**, l'applicazione di una funzione
    - L è una funzione
    - M è il parametro attuale che viene applicato alla funzione (ossia, che prende il posto della variabile sostituita)


### Lamba calcolo nei linguaggi mainstream

**CURIOSITà**: Nei linguaggi moderni le lambda expression si chiamano in questo modo perchè derivano da questa teoria.

In tutti i linguaggi main-stream che offrano funzioni come FCE è possibile un mapping diretto fra:
- funzioni del lambda calcolo
- costrutti funzionali offerti dai linguaggi (spesso, proprio nella forma di funzioni anonime – chiamate proprio lambda expressions)
Tali costrutti possono essere:
- definizioni di normali funzioni con nome
- definizioni di funzioni anonime (lambda) assegnate a variabili
 
**MA attenzione al tipo**:
- nel lambda calcolo, funzioni e argomenti sono **untyped**: si lavora per semplici sostituzioni di testo
- nei linguaggi, invece, esiste sempre un **sistema di tipi a cui occorre conformarsi** (più o meno stringente)

**Prima lezione del lamba calcolo**: Per invocare una funzione, darle un nome non è strettamente necessario!

    ((x) => expr)(arg)          equivale a:          (*lambda*_x.expr)(arg)

### Definizione della semantica


**Concetto di riduzione**: (*lambda*_x.L)M → L[M/x]
sostituisco dentro al lambda termine L tutte le x con delle M

**OSS:** Si ha una riduzion in corrispondenza di una chiamata di funzione

**PER ESEMPI CONTORTI**: A volte è utile pensare agli argomenti come stringhe nei primi passaggi. E poi vedere se sono altre funzioni e come applicarle nei passaggi successivi

### Currying
Nel lambda calcolo si dispone solamente di funzioni ad un argomento (mattone elementare) ma nonostante questo non si è limitati. 

Possiamo definire una funzione che **chiude** una funzione di ordine inferiore che mangia il secondo argomento che a sua volta può chiudere un'altra funzione per il terzo argomento ecc.

... slide 25 ha un bell'esempio ...

**Chiaramente è più comodo appiattire i livelli e avere le funzioni a più argomenti, ma il fondamento è questo ed espresso in questo modo e più comodo fare dimostrazioni matematiche. Ma sopratuto non è che il currying ha dei casi di utilizzo particolari?**

**STIAMO PERDENDO QUALCOSA??? CENTRANO QUALCOSA LE FUNZIONI CON UN NUMERO DI ARGOMENTI VARIABILE???**

**PERCHè JS ha queste cose???**

### FORME NORMALIZZATE
Un lambda termine è in forma normale se non si possono applicare altre riduzioni
- ciò può dipendere dall'ordine con cui si riduce

Si dice che un Lambda termine è:
- fortemente normalizzabile, se qualunque sequenza di riduzioni porta a una forma normale
- debolmente normalizzabile, se esiste almeno una sequenza di riduzioni che porta a una forma normale
- non normalizzabile se non si arriva mai a una forma normale

### La computazione del lamba calcolo termina o no?
Anche qua possiamo andare in loop (d'altronde siamo turing equivalenti). Ad es. funzione notevole W

**Teorema di Church-Rosser**:
Ogni lambda ha al più una forma normale
- essa può quindi essere interpretata come risultato
- il lambda-calcolo è deterministico: i grafi aciclici hanno una e una sola foglia

Ma.. siamo sicuri di raggiungerla?
- sì, se il termine è fortemente normalizzabile
- no, se il termine è solo debolmente normalizzabile

In tal caso è cruciale scegliere il "giusto" **ordine di valutazione** per non incappare nel "ramo infinito" del grafo
    -> importanza della strategia di valutazione
