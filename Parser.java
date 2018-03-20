import java.util.Stack;

/**
 * Created by Neville Varney-Horwitz on 2018-03-19.
 */
public class Parser {

    private int i;
    private Token[] input;
    private NonTerminalNode parseTree;

    Parser(Token[] input){
        i = input.length - 1;
        this.input = input;
    }

    void parse() {
        parseTree = new NonTerminalNode(NonTerminal.S);
        S(parseTree);
    }

    NonTerminalNode getParseTree() {
        return parseTree;
    }

    private void advance() {
        i--;
    }

    private void consume(Token t, NonTerminalNode n) {
        if (input[i] == t) {
            n.children.push(new TerminalNode(t));
            if (i > 0)
                advance();
        }
        else
            System.out.println("Error on token " + i);
    }

    private void S(NonTerminalNode n) {
        NonTerminalNode m = new NonTerminalNode(NonTerminal.Body1);
        body1(m);
        n.children.push(m);
        consume(Token.BOF,n);
    }

    private void body1(NonTerminalNode n) {
        switch (input[i]) {
            case CLOSE_CURLY:
                consume(Token.CLOSE_CURLY,n);
                NonTerminalNode m = new NonTerminalNode(NonTerminal.Body2);
                body2(m);
                n.children.push(m);
                break;
            default:
                error();
        }
    }

    private void body2(NonTerminalNode n) {
        switch (input[i]) {
            case SEMICOLON:
                consume(Token.SEMICOLON,n);
                NonTerminalNode m = new NonTerminalNode(NonTerminal.Statement);
                statement(m);
                n.children.push(m);
                m = new NonTerminalNode(NonTerminal.Body2);
                body2(m);
                n.children.push(m);
                break;
            case OPEN_CURLY:
                consume(Token.OPEN_CURLY,n);
                break;
            default:
                error();
        }

    }

    private void statement(NonTerminalNode n) {
        switch (input[i]) {
            case ASSIGN:
                consume(Token.ASSIGN,n);
                consume(Token.IDENTIFIER,n);
                NonTerminalNode m = new NonTerminalNode(NonTerminal.Expr);
                expr(m);
                n.children.push(m);
                break;
            case IF:
                consume(Token.IF,n);
                m = new NonTerminalNode(NonTerminal.Body1);
                body1(m);
                n.children.push(m);
                m = new NonTerminalNode(NonTerminal.Body1);
                body1(m);
                n.children.push(m);
                m = new NonTerminalNode(NonTerminal.Expr);
                expr(m);
                n.children.push(m);
                break;
            case WHILE:
                consume(Token.WHILE,n);
                m = new NonTerminalNode(NonTerminal.Body1);
                body1(m);
                n.children.push(m);
                m = new NonTerminalNode(NonTerminal.Expr);
                expr(m);
                n.children.push(m);
                break;
            default:
                error();
        }
    }

    private void expr1(NonTerminalNode n) {
        switch (input[i]) {
            case CLOSE_PAREN:
                consume(Token.CLOSE_PAREN,n);
                NonTerminalNode m = new NonTerminalNode(NonTerminal.Expr);
                expr(m);
                n.children.push(m);
                consume(Token.OPEN_PAREN,n);
                break;
            default:
                error();
        }
    }

    private void expr(NonTerminalNode n) {
        switch (input[i]) {
            case OPERATION:
                consume(Token.OPERATION,n);
                NonTerminalNode m = new NonTerminalNode(NonTerminal.Expr);
                expr(m);
                n.children.push(m);
                m = new NonTerminalNode(NonTerminal.Expr);
                expr(m);
                n.children.push(m);
                break;
            case IDENTIFIER:
                consume(Token.IDENTIFIER,n);
                break;
            case LITERAL:
                consume(Token.LITERAL,n);
                break;
            case OPEN_PAREN:
                consume(Token.OPEN_PAREN,n);
                break;
            default:
                error();
        }
    }

    private void error() {
        System.out.println("Error on token " + i);
    }
}

enum NonTerminal {
    S,Body1, Body2, Expr, Statement
}

enum Token {
    BOF,
    OPEN_CURLY,
    CLOSE_CURLY,
    SEMICOLON,
    ASSIGN,
    IF,
    WHILE,
    OPEN_PAREN,
    CLOSE_PAREN,
    IDENTIFIER,
    LITERAL,
    OPERATION
}



interface ParseNode {
    String getName();
}
class TerminalNode implements ParseNode {
    Token t;
    TerminalNode(Token t) {
        this.t = t;
    }

    @Override
    public String getName() {
        return t.name();
    }
}
class NonTerminalNode implements ParseNode {
    NonTerminal nt;

    @Override
    public String getName() {
        return nt.name();
    }

    Stack<ParseNode> children = new Stack<>();

    NonTerminalNode(NonTerminal nt) {
        this.nt = nt;
    }



}
