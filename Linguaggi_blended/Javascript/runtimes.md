A runtime environment is where your program runs.

The runtime you choose will determine things like:
- What APIs are available to your code (fetch, canvas, etc.)
- How your code is executed (JIT compiled vs interpreted)
- What dependencies you'll be able to use
- Whether you run on the backend (a server) or frontend (a browser or mobile app)

Examples of Runtimes
- The browser 
    - (we try to pretend they're the same, but in reality different browsers embed different runtimes)
- Node.js
- Deno.js
- Bun

Originally, JavaScript only ran in browsers. Today, it runs almost everywhere.
- the last three are more suited for backend work or for running scripts on your local machine



Generally, every programming language can have multiple runtimes/compilers/interpreters. Generally though, most programming languages have a sort of official main one used my the community. 

Javascript is different from others in this aspect, there isn't an official runtime since JS runs in so many different places   