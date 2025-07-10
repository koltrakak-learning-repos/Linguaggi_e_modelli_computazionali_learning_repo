### async
Asynchronous or async code runs concurrently. While **the main thread continues running subsequent code**, **async tasks are handled outside the main execution flow and run as system resources allow**.

A good example is with the built-in setTimeout() function. setTimeout accepts a function and a number of milliseconds as inputs. It
- sets aside the function to be run after the number of milliseconds has passed,
- at which point it **gets queued for execution** when the main thread is available:

    console.log("I print first");
    setTimeout(
    () => console.log("I print third because I'm waiting 100 milliseconds"),
    100,
    );
    console.log("I print second");

    // Output:
    // I print first
    // I print second
    // I print third because I'm waiting 100 milliseconds

### Why Async?
We try to mostly write synchronous code whenever possible because it's easier to keep track of, and therefore leads to fewer bugs. But sometimes we need our code to be asynchronous. For example, whenever you update your user settings on a website, your browser needs to communicate those new settings to the server. The time it takes your HTTP request to physically travel across all the wiring of the internet can be anywhere from 10-1000 milliseconds (give or take).

It would be excruciating if your webpage froze while waiting for every network request to finish. By making network requests asynchronously, the webpage can continue to execute other code while waiting for the HTTP response to come back.



### Promises
A Promise in JavaScript is very similar to a promise to your friend. It's just a **commitment for the future**. For example, I promise to explain promises to you. This promise to you has 2 potential outcomes:
- It's fulfilled, meaning I eventually explained
- It's rejected, meaning I failed to explain

The **_Promise_ Object** represents the eventual fulfillment or rejection of a promise. In the meantime, **while we're waiting for the promise to be fulfilled, our code continues executing**. 

Detta in un altra maniera, una Promise è un oggetto che rappresenta un valore che potrebbe non essere ancora disponibile, ma lo sarà in futuro, tipicamente come risultato di un'operazione asincrona (es. fetch, lettura file, timer, ecc.).


### Creating a Promise
Here's a promise that, based on random number generation, will resolve and return the string "resolved!" or reject and return the string "rejected!" after 1 second:

```Javascript
const promise = new Promise((resolve, reject) => {
  setTimeout(() => {
    if (getRandomBool()) {
      resolve("resolved!");
    } else {
      reject("rejected!");
    }
  }, 1000);
});

function getRandomBool() {
  return Math.random() < 0.5;
}
```


### Working With Promises
Now that we've created a promise, how do we use it?

The Promise object has _.then_ and _.catch_ methods. 
- Think of _.then_ as the expected follow-up to a promise
- _.catch_ as the "something went wrong" follow-up.

Sono metodi con cui **registrare delle callback**
- If a promise resolves, its .then function will execute.
- If the promise rejects its .catch method will execute.
- **NB**: esegue o una o l'altra, sono callback mutuamente esclusive 

```Javascript
promise
  .then((message) => {
    console.log(`The promise finally ${message}`);
  })
  .catch((message) => {
    console.log(`The promise finally ${message}`);
  });

// if the promise (from the first example) resolves, the output will be:
// The promise finally resolved!

// if the promise rejects, the output will be:
// The promise finally rejected!
```

### Why Promises?

Almost every time you use a promise it will be to handle some form of I/O. I/O, or input/output, refers to when our code needs to interact with systems outside of the (relatively) simple world of local variables and functions.

Common examples of I/O include:
- HTTP requests
    -  JavaScript's built-in fetch() function returns a promise.
- Reading files from the hard drive
- Interacting with a Bluetooth device
- Sending data to a database

Promises help us perform I/O without forcing our entire program to freeze up while we wait for a response.




### Considerazioni
- La sintassi delle promise è disgusting

- Abbiamo che alla creazione dell'oggetto promise viene eseguita immediatamente anche la funzione esecutrice passata come argomento. **L'esecuzione è quindi sincrona**

- Le Promises gestiscono risultati asincroni ma non rendono automaticamente asincrono il codice al loro interno.
    - Serve a coordinare operazioni asincrone come:
        - setTimeout
        - richieste fetch
        - operazioni su file in Node.js
        - Web APIs

- Se all'interno di una promise inserisci una computazione pesante, non asincrona (vedi sopra), quest'ultima viene eseguita sincronamente e blocca il main thread di JS

- le funzione "pesanti" (e.g. setTimeout) sono asincrone e **vengono gestite dall’ambiente** (browser o Node).
    - Chi esegue davvero il lavoro asincrono? Non è JavaScript puro. È l’ambiente di esecuzione!
        - Browser	->  Web APIs, Timer API, Networking, I/O
        - Node.js	->  libuv (thread pool interno), OS-level APIs


- Event Loop + Thread dietro le quinte
    1. Chiedi di fare un lavoro asincrono (es. fetch)
        fetch("https://api.com/dati")
            .then(response => response.json())
            .then(data => console.log(data));

    2. Il browser crea un thread (interno, invisibile a JS)
        - Il fetch non è fatto nel main thread.
        - Viene passato a una Web API del browser, che usa un thread nativo (es. di C++).
        - Quando il risultato è pronto, lo notifica all’Event Loop.

    3. Event Loop mette il .then() in coda
        - Quando la risposta è pronta, la callback del .then() viene messa nella microtask queue.

    4. JS riprende il controllo
        - Appena il call stack è vuoto, l’event loop esegue le microtask (incluso il tuo .then()).