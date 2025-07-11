If JS is single-threaded, **who is responsible for waiting a timer or the responses from a sent request?**

Someone clearly has to do it, otherwise we would not get the results from these operations. The answer is **external APIs**.

Functions like setTimeout() or fetch() are provided by the runtime API, this means that the implementations of these functions reside in the wider context of the host your runtime is running in (browser, node, bun, ..., idk) and are not implemented in JS (typically C++, Rust or systems languages are used).

This is external environment is **NOT single threaded** so it can use multiple threads to handle your async JS operations.

Once this external code has taken care of whatever IO-operation, **the result gets back in JS via arguments to the registered callback that gets placed in the taks/microtask queue**.





### Ripetiamo
Okay, so we understand that:
- There's only one thread in the runtime
- The main thread can't be blocked by asynchronous tasks
- The **results** of asynchronous tasks are pushed into the task queue


So how does the actual concurrency work? In the case of:

    setTimeout(() => {
    console.log("Hi I'm async!");
    }, 1000);

What logic makes sure that the callback function isn't pushed into the task queue until 1000 milliseconds have passed? Or regarding an HTTP request, what logic pushed the network response into the task queue when the request is complete?

The answer is external APIs. Things like setTimeout, fetch, and addEventListener are all examples of external APIs that the browser or Node.js, Deno, or Bun provide.

The JavaScript runtime (your code and the JS engine) are single-threaded, but these external APIs are not! They are tasks that are fired off and in the background, and when they're done, their results are popped back into the task queue for the event loop to handle.