public class Token {
    private String tk;

    public Token(String tk){ 
        this.tk = tk;
    }

    // meglio usare un regexp piuttosto che un'eccezione
    // oppure ancora meglio, implementare una gerarchia di Token
    public boolean isNumber() {
        try { 
            Integer.parseInt(tk);
        }
        catch(NumberFormatException e){
            return false;
        }

        return true;
    }

    public int getAsInt() {
        return Integer.parseInt(tk);
    }

    public String toString(){
        return tk;
    }

    public boolean equals(Object o){
        if (o instanceof String) {
            return this.tk.equals((String)o);
        }
        else if (o instanceof Token) {
            Token that = (Token)o;
            return this.tk.equals(that.tk);
        }
        else 
            return false;
    }
}