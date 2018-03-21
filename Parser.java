import java.util.Stack;

/**
 * Created by Neville Varney-Horwitz on 2018-03-19.
 */
public class Parser {

    private int i;
    private Token[] input;

    Parser(Token[] input){
        i = input.length - 1;
        this.input = input;
    }

    NonTerminalNode getParseTree() {
        return S();
    }

    private void advance() {
        i--;
    }

    private void consume(Token t, NonTerminalNode n) {
        if (input[i] == t) {
            n.addChild(new TerminalNode(t));
            if (i > 0)
                advance();
        }
        else
            System.out.println("Error on token " + i);
    }


    private NonTerminalNode S() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.S);
        switch (input[i]) {
            case CLOSE_CURLY:
                n.setProduction(1);
                n.addChild(body1());
                consume(Token.BOF,n);
                break;
            default:
                error();
        }

        return n;
    }

    private NonTerminalNode body1() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Body1);
        switch (input[i]) {
            case CLOSE_CURLY:
                n.setProduction(2);
                consume(Token.CLOSE_CURLY,n);
                n.addChild(body2());
                break;
            default:
                error();
        }
        return n;
    }

    private NonTerminalNode body2() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Body2);
        switch (input[i]) {
            case SEMICOLON:
                n.setProduction(3);
                consume(Token.SEMICOLON,n);
                n.addChild(statement());
                n.addChild(body2());
                break;
            case OPEN_CURLY:
                n.setProduction(4);
                consume(Token.OPEN_CURLY,n);
                break;
            default:
                error();
        }
        return n;
    }

    private NonTerminalNode statement() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Statement);
        switch (input[i]) {
            case ASSIGN:
                n.setProduction(5);
                consume(Token.ASSIGN,n);
                consume(Token.IDENTIFIER,n);
                n.addChild(expr());
                break;
            case IF:
                n.setProduction(6);
                consume(Token.IF,n);
                n.addChild(body1());
                n.addChild(body1());
                n.addChild(expr());
                break;
            case WHILE:
                n.setProduction(7);
                consume(Token.WHILE,n);
                n.addChild(body1());
                n.addChild(expr());
                break;
            default:
                error();
        }
        return n;
    }
/*
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
*/
    private NonTerminalNode expr() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Expr);
        switch (input[i]) {
            case OPERATION:
                n.setProduction(8);
                consume(Token.OPERATION,n);
                n.addChild(expr());
                n.addChild(expr());
                break;
            case IDENTIFIER:
                n.setProduction(9);
                consume(Token.IDENTIFIER,n);
                break;
            case LITERAL:
                n.setProduction(10);
                consume(Token.LITERAL,n);
                break;
            default:
                error();
        }
        return n;
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
    final Token t;
    TerminalNode(Token t) {
        this.t = t;
    }

    @Override
    public String getName() {
        return t.name();
    }
}
class NonTerminalNode implements ParseNode {
    final NonTerminal nt;
    int production = -1;

    private Stack<ParseNode> children = new Stack<>();

    @Override
    public String getName() {
        return nt.name();
    }

    NonTerminalNode(NonTerminal nt) {
        this.nt = nt;
    }

    void addChild(ParseNode n) {
        children.push(n);
    }

    Stack<ParseNode> getChildren() {
        return children;
    }

    void setProduction(int production) {
        this.production = production;
    }

}
