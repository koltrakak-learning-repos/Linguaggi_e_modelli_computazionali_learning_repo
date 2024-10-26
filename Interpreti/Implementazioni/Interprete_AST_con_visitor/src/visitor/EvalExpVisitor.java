package visitor;

import exp.*;

public class EvalExpVisitor implements ExpVisitor {
    int value;

    public int getResult() {
        return value;
    }

    public void visit(PlusExp e) {
        e.left().accept(this);
        int arg1 = getResult();
        e.right().accept(this);
        int arg2 = getResult();

        value = arg1 + arg2;
    }

    public void visit(MinusExp e) {
        e.left().accept(this);
        int arg1 = getResult();
        e.right().accept(this);
        int arg2 = getResult();

        value = arg1 - arg2;
    }

    public void visit(MulExp e) {
        e.left().accept(this);
        int arg1 = getResult();
        e.right().accept(this);
        int arg2 = getResult();

        value = arg1 * arg2;
    }

    public void visit(DivExp e) {
        e.left().accept(this);
        int arg1 = getResult();
        e.right().accept(this);
        int arg2 = getResult();

        value = arg1 / arg2;
    }

    public void visit(PowExp e) {
        e.left().accept(this);
        int base = getResult();
        e.right().accept(this);
        int esponente = getResult();

        int tmp = base;
        for (int i = esponente; i > 1; i--) {
            tmp = tmp * base;
        }

        value = tmp;
    }

    public void visit(NumExp e) {
        value = e.getValue();
    }    
}