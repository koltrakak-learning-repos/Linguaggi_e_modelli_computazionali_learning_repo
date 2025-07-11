## Event loop
So how does JavaScript manage to be so efficient with asynchronous code? The answer is the event loop.

The event loop is a single-threaded, non-blocking, **event-driven**, asynchronous **execution model**.

Before understandig the event loop, we need to understand the call stack and the task queue (sometimes called message queue)



### Call stack
So, we know that JavaScript has **one main thread**, and that it's **non-blocking**. This begs the question: **how do these "background" tasks** (like HTTP requests, setTimeout, etc.) **get executed?** Well, it's via the event loop - but first, we need a little refresher on the call stack.


**little refresher**
Every time a function is called, it gets added to the top of the call stack. When the function returns, it gets popped off the stack.

Let's say we have this code:
```Javascript
function startJob() {
  console.log("Job started");
  workOnJob();
}

function workOnJob() {
  console.log("Working on job");
  finishJob();
}

function finishJob() {
  console.log("Job finished");
}

startJob();
```
The call stack will grow like this as each function is called:

                                        -> finishJob
                            -> workOnJob    workOnJob
    [empty]    -> startJob     startJob     startJob

Then as each function returns, it gets popped off the stack:

    finishJob  ->
    workOnJob     workOnJob ->
    startJob      startJob     startJob  -> [empty]

Long story short - JavaScript's call stack works the same way as any other language's call stack. But **what happens when we encounter asynchronous code?** Enter the task queue.



### Task queue
The task queue (also known as the "message queue") is **where the callbacks of asynchronous COMPLETED tasks are queued up to be processed**. It's just a standard queue of things for our JS engine to do, nothing to be scared of. 

The **rule of the task queue** is simple: 
- when the call stack is empty, the event loop (managed by the JS runtime) checks the task queue.
- If there are tasks in the queue, it pushes the first one onto the call stack to be executed.

Take a look at this example again:
```Javascript
function startJob() {
  setTimeout(() => {
    console.log("Hi I'm async!");
  }, 0);
  console.log("Job started");
  workOnJob();
}

function workOnJob() {
  console.log("Working on job");
  finishJob();
}

function finishJob() {
  console.log("Job finished");
}
```

Because the setTimeout says "run this 0 milliseconds from now", you might expect its callback to run instantly and produce this output:

    Hi I'm async!
    Job started
    Working on job
    Job finished

But this is what actually happens:

    Job started
    Working on job
    Job finished
    Hi I'm async!

Because the callback:

    () => {
        console.log("Hi I'm async!");
    };

Was pushed into the task queue to be executed after the call stack is empty, and it's not empty until the final nested function finishJob returns.


### when do the task queue callback get to run? 
this is the job of the event loop!





### Microtask queue
Okay so there's one more queue to be aware of: the microtask queue.

Just like the task queue, the microtask queue is a mechanism for scheduling tasks to be executed later. But it operates under different rules and is used for different purposes. The nature of microtasks is that they represent smaller, shorter-lived operations compared to tasks in the task queue. And importantly, **promises use the microtask queue to schedule their .then() and .catch() callbacks**.

There are two important differences between the task queue and the microtask queue:
- Order of Execution: All microtasks are executed before the next task in the task queue.
- Addition of Microtasks: Microtasks can add more microtasks to the queue, and those will still execute before the next "macro" task.

**So Do I Need to Care?**
Well, usually... no. But sometimes yes. For the most part, you can think about promises and callbacks as just "asynchronous operations that will run later". You typically won't (and its often a bad sign if you do) care about the exact order that their callbacks will run.

But I believe in learning stuff, so let's dive in. This example shows the difference between the "macro" (regular) task queue and the microtask queue:

    function main() {
        console.log("main start");

        setTimeout(() => {
            console.log("macrotask 1 finished");
        }, 0);

        Promise.resolve()
            .then(() => {
            console.log("microtask 1 finished");
            })
            .then(() => {
            console.log("microtask 2 finished");
            });

        console.log("main end");
    }

    main();

It prints:

    main start
    main end
    microtask 1 finished
    microtask 2 finished
    macrotask 1 finished

The important thing to note is simply that all the microtasks run before the next task in the task queue.

**tldr**
- the callback from promises get added here
- gets priority over the task queue