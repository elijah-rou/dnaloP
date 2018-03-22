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
    OPERATION,
    ERROR,
    EOF
}

public class TerminalPair {
    final TerminalToken token;
    final String value;
    TerminalPair(TerminalToken token, String value){
        this.token = token;
        this.value = value;
    }
}