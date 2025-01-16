package visitor;

import exp.*;

public class ParseExpVisitor implements ExpVisitor {
    String curs = "";

    public String getResult() { 
        return curs;
    } 

    protected void visitOpExp(OpExp e){
        e.left().accept(this);
        String sleft = getResult();     //salvo nello stack il risultato di ritorno
        e.right().accept(this);
        String sright = getResult();    //salvo nello stack il risultato di ritorno

        curs = "(" + e.myOp() + " " + sleft + " " + sright + ")";
    }

    public void visit( PlusExp e ) {
        visitOpExp(e);
    }
    
    public void visit( MinusExp e ) {
        visitOpExp(e);
    }

    public void visit( MulExp e ) {
        visitOpExp(e);
    }
    
    public void visit( DivExp e ) {
        visitOpExp(e);
    }
    
    public void visit( PowExp e ) {
        visitOpExp(e);
    }
    
    public void visit( NumExp e ) {
        curs = "" + e.getValue();
    }
}