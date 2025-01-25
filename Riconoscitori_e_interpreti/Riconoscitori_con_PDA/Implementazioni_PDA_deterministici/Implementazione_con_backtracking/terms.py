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
from chars_e_modifiers import *
from items import *


@dataclass
class Term:
    item: Item
    modifier: Modifier | None

#   Term        ::= Item Modifier | Item
def term(seq: list[str]) -> None | tuple[Term, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None

    if item(seq):
        parsed_item, after_item = item(seq)
        if modifier(after_item):
            parsed_modifier, after_modifier = modifier(after_item)
            return Term(parsed_item, parsed_modifier), after_modifier
        else:
            return Term(parsed_item, None), after_item
        
    return None