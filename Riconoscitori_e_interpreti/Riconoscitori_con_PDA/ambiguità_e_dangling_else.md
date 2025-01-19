Nel costrutto if/then/else
- un if può essere innestato in un altro if
- ma la parte else è opzionale

**a quale if va associato l' else?**

Tipicamente si desidera che sia **quello più interno**, ma affinché sia così **occorre definire con attenzione le regole grammaticali**, quelle più ovvie infatti sono **ambigue**.

(perchè è un problema l'ambiguità -> semantica)

# OSS EPICA PER ESAME:
```
Statement → IfStatement | WhileStatement | BeginStatement | AssignStatement 
IfStatement → if Cond then Statement
IfStatement → if Cond then Statement else Statement
```

Queste due produzioni della grammatica sono **sostanzialmente LL(1)** e quindi riconoscibili seppur ambigue (bell'esempio da portare all'esame).
- notiamo che nelle grammatiche sostanzialmente LL(1), il conflitto è in realtà finto in quanto
    - sicuramente la parte comune la devo analizzare
    - successivamente, **arrivati alla parte che cambia**, controllo che strada devo prendere
    - quel "arrivati alla parte che cambia" è la solita soluzione al **non determinismo**, aspetto di avere più info (il primo token della parte che cambia) per andare a colpo sicuro.

Con queste regole però, la parte else potrebbe essere associata sia all’if più interno, sia a quello più esterno 
- **ambiguità**

**Per risolvere il problema occorrono regole più specifiche**

Idea di fondo: **prima della parola chiave *else* può esserci solo un *if/then/else* completo**, non un semplice *if/then*
- ciò garantisce che non ci sia mai un else «esterno» senza che ci sia anche il suo omologo più «interno»
- se voglio un *else* ad una certa profondità, tutti gli *if* più interni devono avere anche loro un *else* in modo da evitare ambiguità 

A tal fine occorre **distinguere già a livello di metasimboli**:
- **l’if/then/else incompleto**
    - metasimbolo IfStatement
- **dall’if/then/else sicuramente completo**
    - nuovo metasimbolo IfElseStat

Nuova grammatica:

Statement → IfStatement | WhileStatement | BeginStatement | AssignStatement     // IfElseStat non è uno Statement
IfStatement → if Cond then Statement                                            // se non ho else non mi preoccupo di nulla
IfStatement → if Cond then IfElseStat else Statement                            // se ho degli else devono essere bilanciati
IfElseStat → if Cond then IfElseStat else IfElseStat

IfElseStat → if Cond then WhileStatement else WhileStatement
IfElseStat → ...tutte le altre combinazioni di Statement


**TAKEWAY IMPORTANTE**:
Anche questo, è un linguaggio in cui l'ordine di derivazione della frase cambia la semantica (ordine degli else) è importante derivare quindi correttamente! In questo senso, abbiamo imparato che l'ambiguità di una grammatica:
- **NON influenza il riconoscimento sintattico della frase**
- tuttavia, complica la parte di **analisi semantica**

Utilizzando la prima grammatica ambigua il parsing LL(1), **avremmo dovuto fare backtracking** nel caso la derivazione fosse stata quella in cui l'else era associato all'if più esterno (complessità computazionale più elevata).