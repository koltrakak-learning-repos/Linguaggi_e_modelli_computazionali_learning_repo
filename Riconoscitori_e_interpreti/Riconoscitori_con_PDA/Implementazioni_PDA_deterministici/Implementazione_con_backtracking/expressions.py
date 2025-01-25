# Grammatica:
#
#   Start       ::= Expression | epsilon
#   Expression  ::= Term Expression | Term
#   Term        ::= Item Modifier | Item
#   Modifier    ::= '*' | '+'
#   Item        ::= Character | Group
#   Group       ::= '(' Expression ')'
#   Character   ::= 'A' | 'B' | 'C' | ... | 'z' 

from dataclasses import dataclass
from terms import *



@dataclass
class Expression:
    term: Term
    next: 'Expression' # riferimento ricorsivo diretto non supportato

#   Expression  ::= Term Expression | Term
def expression(seq: list[str]) -> None | tuple[Expression, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None

    if term(seq):
        parsed_term, after_term = term(seq)
        if expression(after_term):
            parsed_exp, after_exp = expression(after_term)
            return Expression(parsed_term, parsed_exp), after_exp
        else:
            return Term(parsed_term, None), after_term
        
    return None

