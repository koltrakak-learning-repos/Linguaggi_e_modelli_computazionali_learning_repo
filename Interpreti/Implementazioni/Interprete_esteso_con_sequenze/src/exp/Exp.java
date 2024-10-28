package exp;

import visitor.*;

public abstract class Exp {
    abstract public void accept(ExpVisitor v);
}
