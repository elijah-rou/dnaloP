import java.util.Stack;
import java.util.ArrayList;

/**
 * Created by Neville Varney-Horwitz on 2018-03-19.
 */
public class Parser {

    private int i;
    private ArrayList<TerminalPair> input = new ArrayList<TerminalPair>();



    Parser(ArrayList<TerminalPair> input){
        this.input = input;
        i = input.size() - 1;

    }

    NonTerminalNode getParseTree() {
        return S();
    }

    private void advance() {
        i--;
    }

    private void consume(TerminalToken t, NonTerminalNode n) {
        if (input.get(i).token == t) {
            n.addChild(new TerminalNode(input.get(i)));
            if (i > 0)
                advance();
        }
        else
            System.out.println("Error on token " + i);
    }

    private NonTerminalNode S() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.S);
        TerminalToken t = input.get(i).token;
        if(t == TerminalToken.EOF){
            n.setProduction(1);
            consume(TerminalToken.EOF,n);
            n.addChild(body1());
            consume(TerminalToken.BOF,n);
            /*
            if (i != 0)
                //error
            break;
            */
        }
        else{
            //return error("Expected ");
        }

        return n;
    }

    private NonTerminalNode body1() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Body1);
        TerminalToken t = input.get(i).token;
        if(t == TerminalToken.CLOSE_CURLY){
            n.setProduction(2);
            consume(TerminalToken.CLOSE_CURLY,n);
            n.addChild(body2());
        }
        else{
            // return error
        }
        return n;
    }

    private NonTerminalNode body2() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Body2);
        TerminalToken t = input.get(i).token;
        if(t == TerminalToken.SEMICOLON){
            n.setProduction(3);
            consume(TerminalToken.SEMICOLON,n);
            n.addChild(statement());
            n.addChild(body2());
        }
        else if(t == TerminalToken.OPEN_CURLY){
            n.setProduction(4);
            consume(TerminalToken.OPEN_CURLY,n);
        }
        else{
            // error
        }
        return n;
    }

    private NonTerminalNode statement() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Statement);
        TerminalToken t = input.get(i).token;
        if(t == TerminalToken.ASSIGN){
            n.setProduction(5);
            consume(TerminalToken.ASSIGN,n);
            consume(TerminalToken.IDENTIFIER,n);
            n.addChild(expr());
        }
        else if(t == TerminalToken.IF){
            n.setProduction(6);
            consume(TerminalToken.IF,n);
            n.addChild(body1());
            n.addChild(body1());
            n.addChild(expr());
        }
        else if(t == TerminalToken.WHILE){
            n.setProduction(7);
            consume(TerminalToken.WHILE,n);
            n.addChild(body1());
            n.addChild(expr());
        }
        else{
            //error();
        }
        return n;
    }

    private NonTerminalNode expr() {
        NonTerminalNode n = new NonTerminalNode(NonTerminal.Expr);
        TerminalToken t = input.get(i).token;
        if(t == TerminalToken.OPERATOR){
            n.setProduction(8);
            consume(TerminalToken.OPERATOR,n);
            n.addChild(expr());
            n.addChild(expr());
        }
        else if(t == TerminalToken.IDENTIFIER){
            n.setProduction(9);
            consume(TerminalToken.IDENTIFIER,n);
        }
        else if(t == TerminalToken.LITERAL){
            n.setProduction(10);
            consume(TerminalToken.LITERAL,n);
        }
        else{ 
            //error();
        }
        return n;
    }

    private TerminalNode error(String message) {
        System.out.println("Error on token " + i + '\n' + message);
        return new TerminalNode(new TerminalPair(TerminalToken.ERROR, "ERROR"));
    }
}

enum NonTerminal {
    S,
    Body1,
    Body2,
    Expr,
    Statement
}

interface ParseNode {
    String getName();
}
class TerminalNode implements ParseNode {
    final TerminalPair t;
    TerminalNode(TerminalPair t) {
        this.t = t;
    }

    @Override
    public String getName() {
        return t.toString();
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
