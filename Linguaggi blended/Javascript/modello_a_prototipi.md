## Modello a prototipi
A differenza degli altri linguaggi, JavaScript adotta fin dalle origini un modello diverso, object-based ma non object-oriented. In cui il concetto di classe viene sostituito dal concetto di prototipo.

- Ad ogni oggetto viene associato dal suo costruttore (oppure si usa un default) un "oggetto padre", il suo prototipo appunto, di cui eredita le proprietà: si parla perciò di **prototype-based inheritance**.

Prima di parlare di prototipi però abbiamo bisogno di definire un po' di roba...

### Definizione di oggetti
Nel modello a prototipi, un oggetto può essere definito sostanzialmente in due modi:
- tramite un object literal, che introduce un singleton
    - non stabilisce relazioni con altri oggetti, né «parentele» fra essi
    - un oggetto di questo tipo ha come prototipo l'oggetto padre
- tramite un costruttore, ossia una funzione particolare, che inizializza l’oggetto ed è invocata per via indiretta, tramite **l’operatore new**

Tutte le proprietà sono pubbliche e modificabili, inoltre, si possono aggiungere e togliere proprietà dinamicamente.

Le proprietà in generale sono pubbliche. Proprietà private possono essere modellate tramite chiusure, che chiudono apposite funzioni-accessor.


### Keyword this
Eventuali "metodi" (non c'è il concetto di classe e quindi è un nome un po' improprio) di un oggetto possono fare riferimento alle proprietà dell’oggetto in cui vengono definite tramite la keyword **this**. 

**OSS**: la keyword this diventa uno strumento di **metaprogrammazione**; permette ai metodi di un oggetto di definirne, eliminare e sovrascrivere proprietà dell'oggetto a runtime.

**NB**: i metodi NON possono essere scritti come lambda expressions, perché queste ultime **non incorporano il riferimento a this**.

### DOMANDA | CHIEDI PER THIS e METAPROGRAMMAZIONE e OGGETTO GLOBALE



## Costruttori

**OSS**: L'unica differenza tra una funzione normale ed un costruttore è che all'interno di quest'ultimo ci sono delle proprietà riferite con *this*

Un costruttore è una normale funzione, che però:
- ha per convenzione un nome che inizia per lettera maiuscola
- è invocata indirettamente, tramite l'operatore **new**
- fa riferimento al suo interno alle proprietà dell’oggetto tramite **this**

**Esempio**:

    Point = function(i,j) {
        this.x = i;
        this.y = j;
        
        this.getX = function(){ return this.x; }
        this.getY = function(){ return this.y; }
        
        this.toString = function() {
            return `Punto (${this.x},${this.y})`;
        }
    }

L’esempio sopra definisce un costruttore che, quando invocato, costruisce un oggetto con 5 **proprietà**: x, y, getX, getY, toString

**ATTENZIONE**: sarebbe stato diverso scrivere questo:

Point = function(i,j) {
    x = i;
    y = j;
    
    getX = function(){ return this.x; }
    getY = function(){ return this.y; }
    
    toString = function() {
        return `Punto (${this.x},${this.y})`;
    }
}

senza "this" non si sta più accedendo (e definendo) alle **proprietà** dell'oggetto riferito dal costruttore, si stanno semplicemento definendo delle variabile implicitamente.

**ATTENZIONE_2**: una funzione costruttore va invocata **implicitamente** con *new* dato che il runtime deve fare un po' di cose:
- bisogna innanzitutto creare un oggetto vuoto a cui verranno aggiunte le proprietà del construttore marchiate da *this*
- bisogna fare un binding tra l'oggetto creato e il riferimento puntato da *this* nella funzione costrutture
    - altrimenti *this* punterebbe all'oggetto globale (che è quello che succede quando si invoca senza *new*) 
- infine bisogna restituire l'oggetto creato. Senza new questo non avverrebbe.

**OSS_FINALE**: con funzione costruttrice e operatore new, di fatto è come se si chiama la funzione costruttrice con argomento nascosto uguale alla memoria necessaria.

### Costruttori vs classi
Nel modello a prototipi i costruttori sostituiscono le classi, catturando le categorie di oggetti da rappresentare
- sono «tutto ciò che resta» delle classi

MA: mentre le classi fissano la struttura (campi dati) e il comportamento (metodi) delle future istanze, i costruttori stabiliscono solo lo **stato iniziale** degli oggetti futuri, che potranno poi evolvere indipendentemente.
- le classi fissano una struttura immutabile, i prototipi forniscono una struttura iniziale mutabile

Per questo motivo, __ogni oggetto tiene traccia del costruttore che lo ha creato__ nella sua proprietà **_constructor_**
- l'oggetto, nel frattempo, potrebbe essere diventato irriconoscibile rispetto alla struttura iniziale datagli dal costruttore


## Prototipi di oggetti
Nel modello a prototipi, ogni oggetto è associato a un "oggetto padre", detto prototipo, di cui __eredita le proprietà__
- un prototipo è quindi innanzitutto un normale oggetto
- tale prototipo è referenziato internamente tramite una proprietà nascosta, chiamata \_\_proto\_\_

Poiché tale prototipo è a sua volta un oggetto, anch'esso avrà a sua volta un prototipo "padre"; nasce una **gerarchia di ereditarietà prototipale**

Ogni oggetto possiede quindi sia proprietà proprie, sia tutte quelle ereditate dal padre e da tutti i suoi antenati
- si ha ereditarietà tra oggetti e non tra classi. Qua è tutto più fluido, non ho la (conveniente) rigidità delle classi

**Gli oggetti costruiti dallo stesso costruttore condividono lo stesso prototipo**, assegnato loro dal costruttore
- sono i costruttori a creare il campo \_\_proto\_\_ che popolono con i prototipi degli oggetti che creano

### Ereditarietà prototipale    
Tutti i tipi di dati predefiniti in JavaScript hanno il loro
prototipo «progenitore»
- gli object literal puntano al prototipo capostipite della gerarchia
- tutti i numeri fanno riferimento al prototipo dei numeri
- tutte le funzioni fanno riferimento al prototipo delle funzioni
- tutti gli array fanno riferimento al prototipo degli array
- … e così via

Chi sono costoro?
- il prototipo dei numeri è esso stesso un numero
- il prototipo delle funzioni è esso stesso una funzione
- il prototipo degli array è esso stesso un array
- … e così via

In pratica, ogni prototipo progenitore è il **primo oggetto del suo tipo**.

**OSS**: le categorie di oggetti sono **uniformi** -> non esistono tipi primitivi

**OSS_2**: dato che i prototipi forniscono il fondamento su cui si basano le proprietà degli oggetti creati, il campo \_\_proto\_\_ è spesso hidden o non modificabile.

**OSS_3**: il costruttore **Object** non è l'oggetto capostipite, è solo una funzione come l'altra


...

I costruttori devono anche riempire il campo \_\_proto\_\_ degli oggetti che costruiscono. Come funziona questo riempimento?

Innanzitutto, **È PROGRAMMABILE!** Praticamente, le funzioni costruttore hanno una proprietà chiamata **_prototype_** che punta all'oggetto prototipo per gli oggetti che crea (il costruttore riempe \_\_proto\_\_ degli oggetti creati con il suo campo *prototype* (di default è un oggetto vuoto) ).



... Buona progettazione del modello a prototipi, tutto è uniforme e non ci sono eccezioni ...

...

**THIS IS CRAZY**: In javascript è possibile modificare **A RUNTIME** i prototipi e quindi modificare che cosa viene ereditato

Due soluzioni:
- **retroattiva**: si modifica il prototipo puntato dagli oggetti già creati
- **sostituzione del prototipo di costruzione**: il prototipo puntato dal campo *prototype* del costruttore viene sostituito con uno nuovo.

...

Il messaggio di javascript è che non ti serve necessariamente il concetto di classe! Ti basta l'ereditarietà tra oggetti.






### CHIEDI PERCHÈ L'OGGETTO P1 BIANCO HA I CAMPI X E Y

### CHIEDI INFO PER L'ESAME e PRENOTATI 