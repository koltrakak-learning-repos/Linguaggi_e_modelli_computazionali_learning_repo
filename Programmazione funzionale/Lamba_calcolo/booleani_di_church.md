### Come implementare i boolean di church in un linguaggio tipato fortemente come Java

Dieci slide di sofferenza in Java: vabbè, qua voleva proprio lamentarsi... che grande

**IMPORTANTE**: Bisogna sapere come mai è problematico cercare di rappresentare questa cosa con un linguaggio tipato fortemente!

Problemi:

1. T ed F sono due funzioni che dato un ingresso di un determinato tipo, restituiscono un'altra funzione che a sua volta: dato un ingresso di un determinato tipo (in generale diverso da quello iniziale) restituisce un risultato di uno dei due tipi appena menzionato. Siccome dove si deve poter passare T si deve anche poter passare F, e siccome queste due funzioni sono denotate da un tipo diverse, si ha necessità di definire anche un supertipo comune. 
    - Function< U, Function< U,U > >    ===     LambdaBool< U >
    - wow l'ho spiegata proprio bene...

2. Alle funzioni not(), and() e or() si passa come primo argomento un "selettore" che in teoria è analogo a T ed F menzionati sopra. Tuttavia questo selettore ha come suoi argomenti non degli U (tipo che si mangiano T e F), ma le **funzioni** T/F (ossia dei LambdaBool< U >). Di conseguenza, gli argomenti di not(), and() e or(), sono degli "higher level" selector, di tipo LambdaBool< LambdaBool< U > > 

3. Il not è tutto sommato ancora comprensibile, or() e and() no grazie ...

### Conclusione
Lesson learned: i type system forti «mal si adattano» a queste manipolazioni di meta-meta livello
- costringono a descrivere tutto in modo complicato, ci si perde la testa!
- quelle manipolazioni sono a casa loro nei linguaggi loosely typed

Nei linguaggi con type system forti conviene usare funzioni a N argomenti, così da «appiattire» i livelli (**no currying!**)
- ma per appiattire davvero bisogna evitare l’argomento-selettore x, il che implica cablare nella funzione l’algoritmo di scelta invece che riceverlo
- e così.. si torna alle funzioni classiche, come il «buon vecchio» if ternario!

**Esempio**: pseudo-codice del not ---> (x==t) ? f : t;
    
    Function<Integer,Integer> nt = x-> (x==33) ? 44 : 33;
    System.out.println("not(33) = " + nt.apply(33));
    System.out.println("not(44) = " + nt.apply(44));