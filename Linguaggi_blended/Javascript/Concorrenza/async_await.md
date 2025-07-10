## Await
The _await_ keyword is **used to wait for a Promise to resolve**. Once it has been resolved, the await expression returns the value of the resolved promise. It's basically a more **modern syntax for .then callbacks**.

- .then Callback
    promise.then((message) => {
        console.log(`Resolved with ${message}`);
    });

- await Syntax
    const message = await promise;
    console.log(`Resolved with ${message}`);

Personally, I recommend using await over .then whenever possible. It's cleaner and easier to read.

NOTA: await locca solo l'interno della funzione async, non tutto il programma. Ma mentre aspetta, il thread è libero di fare altro (quindi non è un blocco "duro").



### Handling Rejections
When using await, if the promise is rejected, it will throw an error. That means we can use **standard try/catch blocks** to handle rejections.

    try {
        const message = await promise;
        console.log(`Resolved with ${message}`);
    } catch (error) {
        console.log(`Rejected with ${error}`);
    }





## Async
While the await keyword can be used in place of .then() to resolve a promise, the async keyword can be used in place of new Promise() to **create a new promise**.

When a function is prefixed with the async keyword, **calling it will automatically return a Promise that resolves to the return value**. You can think of async as **"wrapping" your function within a promise**.

These are equivalent:

New Promise()
```Javascript
    function getPromiseForUserData() {
        return new Promise((resolve) => {
            fetchDataFromServerAsync().then(function (user) {
                resolve(user);
            });
        });
    }

    const promise = getPromiseForUserData();
```
    
Async
```Javascript
    async function getPromiseForUserData() {
        const user = await fetchDataFromServer();
        return user; // nota qua sto restituendo una promise === resolve(user)
    }

    const promise = getPromiseForUserData();
```

**NB**: **await can only be used inside an async function or at the top level of a module (file)**. In an async function, returning a Promise, will implicitly be awaited by the async function.







### then vs await
In the early days of web browsers, promises and the await keyword didn't exist, so the only way to do something asynchronously was to use callbacks.

A "callback function" is a function that you hand to another function. That function then calls your callback later on. The setTimeout function we've used in the past is a good example.

    function callbackFunction() {
        console.log("calling back now!");
    }
    const milliseconds = 1000;
    setTimeout(callbackFunction, milliseconds);


The .then() syntax is generally easier to use than non-Promise callbacks, but async and await make handling promises even simpler. As a general rule, prefer async and await over .then and New Promise()... I mean for realsies, which of the following is easier to understand?
    
```Javascript
fetchRecipient()
    .then(function (recipient) {
        return fetchMessageForRecipient(recipient.id);
    })
    .then(function (message) {
        return fetchDeliveryStatus(message.id);
    })
    .then(function (status) {
        console.log(`The status is ${status}`);
    });
```

or...

```Javascript
const recipient = await fetchRecipient();
const message = await fetchMessageForRecipient(recipient.id);
const status = await fetchDeliveryStatus(message.id);
console.log(`The status is ${status}`);
```

The async and await keywords weren't released until after the .then API, which is why there is still a lot of legacy .then() code out there.