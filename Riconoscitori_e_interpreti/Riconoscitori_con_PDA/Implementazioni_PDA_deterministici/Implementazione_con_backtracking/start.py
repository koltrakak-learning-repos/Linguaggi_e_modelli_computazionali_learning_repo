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
from expressions import *



@dataclass
class Start:
    content: Expression | None

#   Start       ::= Expression | epsilon
def start(seq: list[str]) -> None | tuple[Start, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None

    if expression(seq):
        parsed_exp, after_exp = expression(seq)
        return Start(parsed_exp), after_exp
        
    return Start(None), seq

