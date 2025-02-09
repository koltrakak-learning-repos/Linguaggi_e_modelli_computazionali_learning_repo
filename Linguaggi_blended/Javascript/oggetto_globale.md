JavaScript prevede un ambiente globale, che ospita funzioni e variabili definite fuori da ogni altro scope.

**È un opportuno oggetto** avente:
- come metodi, tutte le funzioni predefinite
    - **inclusi i costruttori**: Object, Array, Boolean, Function, Number, String,…
- più tutte le funzioni e le variabili globali **definite dall'utente**

Non è prestabilito chi o cosa sia: dipende dall'ambiente
- in un browser, tipicamente è l'oggetto window
- in un web worker, tipicamente è l’oggetto self
- in Node.js, tipicamente è l’oggetto global