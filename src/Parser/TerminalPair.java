enum TerminalToken{
    BOF,
    OPEN_CURLY,
    CLOSE_CURLY,
    SEMICOLON,
    ASSIGN,
    IF,
    WHILE,
    IDENTIFIER,
    LITERAL,
    OPERATOR,
    ERROR,
    EOF,
    OPEN_PAREN,
    CLOSE_PAREN
}

public class TerminalPair {
    final TerminalToken token;
    final String value;
    TerminalPair(TerminalToken token, String value){
        this.token = token;
        this.value = value;
    }

    public String toString(){
        return "<"+this.token+", "+value+">";
    }
}