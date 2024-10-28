package visitor;

import exp.*;

public class ParseExpVisitor implements ExpSeqVisitor {
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

    public void visit( SeqExp e ) {
        visitOpExp(e);
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

    public void visit( LIdentExp e ) {
        curs = e.getName();
    }

    public void visit( RIdentExp e ) {
        curs = "$" +  e.getName();
    }
        
    public void visit( AssignExp e ) {
        visitOpExp(e);
    }
}