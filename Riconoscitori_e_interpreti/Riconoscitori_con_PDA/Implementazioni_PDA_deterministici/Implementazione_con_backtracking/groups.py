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
class Group:
    content: 'Expression'

#   Group        ::= '(' Expression ')'
def group(seq: list[str]) -> None | tuple[Group, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None

    if seq[0] == '(':
        if expression(seq[1:]):
            parsed_exp, after_exp = expression(seq[1:])
            if after_exp[0] == ')':
                return Group(parsed_exp), after_exp[1:]

    return None

