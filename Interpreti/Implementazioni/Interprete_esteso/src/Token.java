public class Token {
    private String tk;

    public Token(String tk){ 
        this.tk = tk;
    }

    public boolean isNumber() {
        // doppio \\ perchè in java un backslash singolo è il carattere di escape 
        return tk.matches("^-?\\d+$");
    }

    public boolean isIdentifier() {
        //solo caratteri alfabetici minuscoli o maiuscoli per identificatori
        return tk.matches("^[a-zA-Z]+$");
    }

    public int getAsInt() {
        if( !this.isNumber() )
            throw new IllegalArgumentException(this.tk + " non è un intero");
        
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