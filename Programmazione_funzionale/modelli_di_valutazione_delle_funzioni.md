Ogni linguaggio che introduce funzioni deve prevedere un modello computazionale per la loro **valutazione**.

Tale modello deve stabilire:
- **QUANDO si valutano i parametri**
- COSA si passa alla funzione
- COME si attiva la funzione (sincrono/asincrono?)

Il più comune è il **Modello applicativo** (o call by value)
- QUANDO si valutano i parametri? Subito, prima di ogni altra cosa
- COSA si passa alla funzione? di solito, il valore dei parametri [a volte, per certi tipi o a esplicita richiesta: il loro __indirizzo__]
- COME si attiva la funzione: tipicamente, chiamandola e cedendo il controllo [modello __sincrono__ –> si attende che essa termini]

Modello applicativo, valutando i parametri a priori **potrebbe essere inefficiente e sopratutto causare dei fallimenti non necessari** in alcuni casi:
- infatti, valutando/calcolando i parametri sempre e comunque, questo modello **può fare del lavoro inutile se alcuni di essi non risultano poi necessari in quella chiamata**.
- ma soprattutto, **se la valutazione di uno di tali parametri dà errore (o eccezione), questo modello causa un fallimento che si sarebbe potuto evitare con un approccio diverso (lazy)**.
    - null dereference
    - div by 0

### Modello call by name (o normale)

**Il modello applicativo non è il solo possibile.** 

Il modello normale adotta un meccanismo di valutazione noto come Call-By-Name in cui:
- i parametri non si valutano all'atto della chiamata, ma solo **al momento dell'uso e quindi solo se servono**
- a tal fine, alla funzione si passano non dei valori (o indirizzi) ma degli oggetti computazionali/entità ("eseguibili") ad esempio espressioni
    - vengono passati gli argomenti cosi come sono. Al momento del loro utilizzi verranno valutati- 
- ergo, un parametro ricevuto può non essere mai valutato se in quella invocazione non c'è effettivo bisogno di lui
    - si evita di fallire inutilmente

L'insieme di funzioni che terminano con successo con questo modello è più ampio rispetto al modello applicativo.
- Il vero punto è che qua si valuta solo quello che serve (lazy), e quindi questo modello salva più funzioni rispetto al modello applicativo

### perchè non usare sempre il modello call by name allora?

    Se il modello Call-by-Name è così utile, perché nei linguaggi di largo uso non è stato adottato?

Perché, nonostante i suoi vantaggi concettuali, è meno efficiente nei **casi normali**, che sono la maggioranza:
- si passano delle entità valutabili (ad es. espressioni) e non dei semplici valori e quindi __valuta i parametri più volte__, ogni volta che compaiono all'interno della funzione.
    - si potrebbe ovviare valutandoli solo al primo uso e poi fare caching 
    -  modello call by need Call by Need
- richiede più risorse a run-time (memoria), dovendo gestire il passaggio di oggetti computazionali anziché semplici valori o indirizzi
    - ma anche questo non sarebbe un grande problema

Il vero Motivo principale è che __richiede una macchina virtuale capace di lazy evaluation__, che per quanto bella ha una utilità limitata nella maggior parte dei contesti. 
- ha senso implementarla solo per catturare "pochi"(?) casi, che oltretutto spesso sono errori di programmazione… che ci piacerebbe notare?

Inoltre, un altra critica che si potrebbe fare al modello call by name è che **rende il debugging più difficile**, in quanto la valutazione lazy degli argomenti, potrebbe far scoppiare una funzione solo alcune volte ed anche lontano dal punto che ha generato l'errore. Questo non accada con il modello call by value.

### Curiostità | macro in C
Le macro in C (seppur, quasi per sbaglio) adottano un modello call by name!

Spiegazione: quando si modella una funzione con una macro, non c'è una vera e propria invocazione ma solo una **sostituzione testuale** fatta dal preprocessore; inoltre, combinando questa caratteristica alla **valutazione in corto circuito** delle espressioni condizionali, si ottiene una sorta di valutazione lazy degli argomenti della macro. 





## Come si implementa la Call-by Name
Il modello call-by-name può comunque essere facilmente simulato nei linguaggi con funzioni come FCE.

Più nel dettaglio, bisogna fare a mano, artigianalmente, ciò che il modello call-by-name avrebbe fatto da solo; ossia, passare alla funzione non valori, ma opportuni oggetti computazionali che, quando eseguiti (valutati/invocati), producano i valori desiderati.

Per farlo è sufficiente:
- sostituire ogni valore di parametro attuale con una funzione che ritorni tale valore quando invocata
- sostituire ogni uso di un parametro formale dentro la funzione con una chiamata alla funzione stessa

**OSS**: Se il linguaggio non ha funzioni come first-class entities, occorre simulare anche quel livello. Passando oggetti di una classe con una opportuna funzione da invocare → alquanto contorto! 
Solita lezione, se il linguaggio manca di espressività e non ti viene incontro per permetterti di modellare quello che hai in testa, ti devi arrangiare con quello che hai. Chiaramente in termini di leggibilità, manutenibilità, robustezza, ecc... non c'è paragone.  


... esempio nei vari linguaggi ...
var f = function(flag, x) {         // diventano due funzioni
    return (flag()<10) ? 5 : x();   // invoca le due funzioni
}
var b = f( ()=>3, ()=>{abort()} );  // ora funziona!



Il caso interessante è quello di Scala in cui il lato del chiamante non si rende nemmeno conto di star passando delle funzioni.

In Scala non c'è bisogno di ricorrere al passaggio di funzioni! Il linguaggio supporta __nativamente__ la call by name: basta etichettare con '=>' gli argomenti da passare by name; il runtime di Scala provvederà a gestire le lambda sotto banco, mascherando all'utente quel livello!
- netto salto di espressività!
- Il linguaggio ti viene in contro! 

L'utilizzatore (chiamante) della funzione __non deve catturare i valori in funzioni!__ Può passarli, apparentemente, come semplici valori. Questo permette, ad esempio, al codice dell'utente di non cambiare anche se il lato del chiamato cambia la valutazione del parametro con call by name/value.

Anche se cambia l'universo mondo, il salto di espressivitò dovuto al fatto che il linguaggio ti venga in contro, permette di nascondere dettagli implementativi che interessano solo il lato del chiamato.