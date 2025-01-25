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
from groups import *




@dataclass
class Item:
    content: Character | Group

#   Item        ::= Character | Group
def item(seq: list[str]) -> None | tuple[Item, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None

    # notare il backtracking!
    #   - provo a fare il parsing delle alternative in ordine
    #   - se una strada fallisce, torno indietro e provo la successiva
    # notare anche la priorità
    #   - prima controllo se ho un character e poi se ho un group
    #   - se la grammatica fosse non deterministica questo potrebbe avere delle conseguenze 
    if character(seq):
        parsed_char, after_char = character(seq)
        return Item(parsed_char), after_char
    if group(seq):
        parsed_group, after_group = group(seq)
        return Item(parsed_group), after_group
    
    return None