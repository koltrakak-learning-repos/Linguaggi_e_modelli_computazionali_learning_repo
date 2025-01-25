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

LITERAL_CHARS = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ"
MODIFIER_CHARS = "*+"

@dataclass
class Character:
    content: str

@dataclass
class Modifier:
    content: str


# funzione che data una lista di caratteri 
#   - controlla se il primo appartiene all'insieme dei caratteri validi
#   - restituisce la rappresentazione come dataclass e i caratteri rimanenti
def character(seq: list[str]) -> None | tuple[Character, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None
    if seq[0] in LITERAL_CHARS:
        char = seq[0]
        after_char = seq[1:]
        return Character(char), after_char
    
    return None



# questa invece controlla se il carattere corrente è un modifier
def modifier(seq: list[str]) -> None | tuple[Modifier, list[str]]:
    # Controlla se la lista non è vuota
    if not seq:
        return None
    if seq[0] in MODIFIER_CHARS:
        modifier = seq[0]
        after_modifier = seq[1:]
        return Modifier(modifier), after_modifier
    
    return None