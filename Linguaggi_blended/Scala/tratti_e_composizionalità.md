### slide 223 | perchè c'è stato bisogno del bottom type nothing?
il fatto che tutto sia una espressione implica che tutto abbia anche un tipo! Ed anche il fatto che questi tipi siano compatibili.

Con le istruzioni è facile perchè non denotano nessun valore e quindi nessuno tipo. Però infatti, non sono montabili! 

...

## Tratti
In Java tradizionale (< Java 8), le interfacce non possono contenere codice né dati (al più, costanti)
- grazie a questa limitazione, supportano l'ereditarietà multipla

Scala supera le interfacce classiche, introducendo i tratti!
- più simili a classi che a interfacce 
    - **possono contenere codice e dati (NON parametri di classe, non c'è costruttore)**
    - possono fare override di metodi di una classe che li estende/richiede (con with) 
- come classi e interfacce              -> **definiscono un tipo**
- supportano una nuova forma di composizionalità: il MIX-IN

**Mix-in**:
Una classe estende una superclasse (ereditarietà singola), ma può mixarsi con un numero arbitrario di tratti, che vengono composti assieme mediante linearizzazione.
- risultato: **stackable behaviour**

Più esattamente, una classe può:
- ESTENDERE un solo tratto keyword: extends
- COMPORSI con molti tratti keyword: with

Che differenza c'è?
- se la classe estende un tratto, ne eredita la classe base
- se invece si compone con esso, non ne eredita la classe base

La chiave è una **diversa semantica della keyword _super_**
- i riferimenti super.x a dati o metodi della superclasse sono **risolti staticamente, a compile time**
- i riferimenti super.x a dati o metodi di un tratto, invece, sono **risolti dinamicamente, a run time**

**OSS**: Un'interfaccia Java o C# tradizionale (Java <8, C#<8.0) può essere vista come caso particolare di tratto (senza codice, con sole dichiarazioni)

**Ereditarietà multipla**:
Le __classi__ hanno note difficoltà con l'ereditarietà multipla. Le interfacce «classiche» la supportano perché, essendo senza codice, evitano alla radice i noti rischi di duplicazione dati / metodi.

I __tratti Scala__ invece, bypassano il problema perché, pur potendo contenere dati o metodi:
- non avendo costruttori, non possono inizializzare dati
- c'è sempre e solo un unico costruttore primario (single entry point)
    - quello dell'unica classe da cui un tratto eredita
- la **linearizzazione** stabilisce una forma prestabilita e predicibile di composizione di comportamenti e dati 
    - stackable behaviour

### Composizionalità dei tratti
Un tratto può estendere una classe (o un altro tratto)
- **È una specifica di vincolo**: potrà essere mixato solo con sottoclassi della classe che lui stesso estende
- in assenza di specifiche, s'intende Any
- l'ordine con cui 

es:
    class Numero(num: Int) {
        def calcola = num
    }

    trait Raddoppia extends Numero {
        override def calcola = 2*super.calcola
    }

    trait Incrementa extends Numero {
        override def calcola = 1+super.calcola
    }

Entrambi i tratti ridefiniscono (in modo diverso) il metodo calcola invocando il corrispondente **metodo superiore**
- si ha però un late binding nel decide chi sia questo metodo superiore
- **collegamento dinamico, non statico**

    object Prova{
        def main(args: Array[String]) = {
            println( (new Numero(3) with Raddoppia with Incrementa).calcola )   // 3*2+1    = 7
            println( (new Numero(3) with Incrementa with Raddoppia).calcola )   // (3+1)*2  = 8
        }
    }

Dentro ai tratti, **le chiamate a super sono collegate dinamicamente tenendo conto dell’ordine di composizione**
- stackable behaviour
- Si possono creare dinamicamente **oggetti composti in modo diverso**


**Punto chiave**: non è necessario aver previsto a priori le classi per ogni possibile situazione!
- Si creano specifici oggetti «ad hoc» componendo in modo diverso i tratti

### Linerizzazione
Le chiamate a super sono risolte per linearizzazione
- si percorre la sequenza di extends / with con un criterio prestabilito, ottenendo una lista di strati
- si compongono le chiamate a super seguendo tale lista