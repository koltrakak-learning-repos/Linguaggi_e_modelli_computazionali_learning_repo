package exp;

import visitor.*;

public class RIdentExp extends Exp {
    String name;
    int value;

    public RIdentExp(String v) {
        name = v;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void accept( ExpVisitor v) {
        ((ExpAssignVisitor)v).visit(this);
    }
}
