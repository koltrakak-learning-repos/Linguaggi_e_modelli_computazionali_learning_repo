T = function(x) {
    return function(y) {
        return x
    }
}

F = function(x) {
    return function(y) {
        return y
    }
}

// chiaramente a queste funzioni qua sotto bisogna passare le funzioni di sopra come primi argomenti
not = function(x) {
    return x(F)(T) 
}

and = function(x) {
    return function(y){
        return x(y)(F)
    }
}

or = function(x) {
    return function(y){
        return x(T)(y)
    }
}

// esempi
T(1)(2) 

F(1)(2)

not(T)(1)(2) 

and(F)(1)(1)(2) 

and(T)(T)(1)(2)

or(F)(T)(1)(2)

or(F)(F)(1)(2) 




/* Sintassi con lambda expression */

T = (x) => { return (y) => x }

F = (x) => { return (y) => y }

not = (x) => { return x(F)(T) }

and = (x) => { return (y) => x(y)(F) }

or = (x) => { return (y) => x(T)(y) }

// oppure ancora

T = (x) => (y) => x

F = (x) => (y) => y

not = (x) => x(F)(T)

and = (x) => (y) => x(y)(F)

or = (x) => (y) => x(T)(y) 