blah blah...

### Variabili e Scope
Le variabili in Javascript sono **loosely typed**
- caratteristica chiave per gestire facilmente funzioni come dati, in particolare funzioni di ordine superiore

La **dichiarazione** di una variabile può essere:
- implicita (la si usa e basta)           ->  x = 18
- esplicita con la parola chiave *var*    ->  var x = 19
- esplicita con la parola chiave *let*    ->  let x = 19
La differenza sta nello **scope**:
- una variabile dichiarata **implicitamente**, senza keyword, si intende definita a livello **globale**
- una variabile dichiarata **tramite var** esiste in tutta la **funzione** in cui è definita (per lo scope più esterno, equivale al livello globale)
    - **NB**: A differenza di Java, **un blocco non delimita uno scope per le variabili globali o definite con *var***
- una variabile dichiarata tramite *let* esiste solo nel blocco in cui è definita
    - **NB**: un blocco delimita uno scope per le variabili definite con *let*


### Definizione di Oggetti
In javascript, un'oggetto può essere definito sostanzialmente in due modi:
- tramite un literal (viene creato un singleton)
- tramite un costruttore

Tutte le proprietà degli oggetti sono pubbliche e modificabili, inoltre, **si possono aggiungere e togliere proprietà dinamicamente** (type augmenting). Proprietà private possono essere modellate tramite chiusure, che chiudono apposite funzioni-accessor.

Caratteristiche:
- un object literal elenca semplicemente le proprietà di un oggetto, nella forma «nome: valore»:
    - **non stabilisce relazioni con altri oggetti, né «parentele» fra essi**
- un costruttore definisce invece la «ricetta di costruzione» di oggetti, che saranno quindi **inizialmente tutti identici**

### Keyword this
Eventuali "metodi" (non c'è il concetto di classe e quindi è un nome un po' improprio) di un oggetto possono fare riferimento alle proprietà dell’oggetto in cui vengono definite tramite la keyword **this**.
- La keyword this diventa uno strumento di **metaprogrammazione**
- permette ai metodi di un oggetto di: definire, eliminare e sovrascrivere proprietà dell'oggetto a cui appartengono a runtime.

**NB**: i metodi NON possono essere scritti come lambda expressions, perché queste ultime **non incorporano il riferimento a this**.



## Costruttori
Un costruttore è una normale funzione, che però:
- ha per convenzione un nome che inizia per lettera maiuscola
- è invocata indirettamente, tramite l'operatore **new**
- fa riferimento al suo interno alle proprietà dell’oggetto tramite **this**

Nella definzione, l'unica differenza tra una funzione normale ed un costruttore, è che all'interno di quest'ultimo ci sono delle proprietà riferite con *this*.

**ATTENZIONE**: una funzione costruttore va invocata **implicitamente** con *new* dato che il runtime deve fare un po' di cose:
- bisogna innanzitutto **creare un oggetto vuoto** a cui verranno aggiunte le proprietà del construttore marchiate da *this*
- bisogna fare un binding tra l'oggetto creato e il riferimento definito dalla proprietà *this* nella funzione costrutture
    - altrimenti, *this* punterebbe all'oggetto globale (che è quello che succede quando si invoca un costrutture senza *new*) 
- infine bisogna restituire l'oggetto creato.



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

**ATTENZIONE**, sarebbe stato diverso scrivere questo:
Point = function(i,j) {
    x = i;
    y = j;
    
    getX = function(){ return this.x; }
    getY = function(){ return this.y; }
    
    toString = function() {
        return `Punto (${this.x},${this.y})`;
    }
}

senza "this" non si sta più accedendo (e definendo) alle **proprietà** dell'oggetto riferito dal costruttore, si stanno semplicemento definendo delle variabili (globali) implicitamente.

**ATTENZIONE_2**: invocare un costruttore senza new restituisce undefined, ma modifica anche l'oggetto globale dato che il binding di this all'interno del costruttore viene effettuato con quest'ultimo.