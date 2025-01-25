from start import *

def parse(seq: list[str]) -> Start:
    parsed, rest = start(seq)
    if len(rest) > 0:
        raise ValueError("Failed to parse the whole input")
    return parsed

ast = parse("(ha+)+")
print(ast)