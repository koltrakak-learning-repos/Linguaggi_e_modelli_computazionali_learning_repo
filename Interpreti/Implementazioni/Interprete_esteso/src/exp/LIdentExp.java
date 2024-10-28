package exp;

import visitor.*;

public class LIdentExp extends Exp {
    String name;

    public LIdentExp(String v) {
        name = v;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void accept(ExpVisitor v) {
        ((ExpAssignVisitor)v).visit(this);
    }
}
