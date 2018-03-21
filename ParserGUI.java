import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

/**
 * Created by Neville Varney-Horwitz on 2018-03-20.
 */
public class ParserGUI extends JFrame {
    static Token[][] sampleInputs = new Token[][]{
            {
                    Token.BOF,
                    Token.OPEN_CURLY,
                    Token.LITERAL, Token.LITERAL, Token.OPERATION, Token.LITERAL, Token.OPERATION, Token.IDENTIFIER, Token.ASSIGN, Token.SEMICOLON,
                    Token.CLOSE_CURLY
            },{
            Token.BOF, //beginning of file
            Token.OPEN_CURLY,
            Token.LITERAL, Token.IDENTIFIER, Token.ASSIGN, Token.SEMICOLON,
            Token.IDENTIFIER, Token.LITERAL, Token.OPERATION,
            Token.OPEN_CURLY,
            Token.IDENTIFIER, Token.LITERAL, Token.OPERATION, Token.IDENTIFIER, Token.ASSIGN, Token.SEMICOLON,
            Token.CLOSE_CURLY,
            Token.OPEN_CURLY, Token.CLOSE_CURLY,
            Token.IF, Token.SEMICOLON,
            Token.CLOSE_CURLY
    }};

    public static void main(String[] args) {
        ParserGUI frame = new ParserGUI();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }

    ParserGUI() {
        super("Parser");
        Parser parser = new Parser(sampleInputs[1]);
        mxGraph g = generateGraph(parser.getParseTree());
        mxGraphComponent graphComponent = new mxGraphComponent(g);
        getContentPane().add(graphComponent);
    }

    private void generateGraph(mxGraph g, Object vParent, NonTerminalNode n) {

        Object vChild;
        ParseNode child;
        for (int j = n.getChildren().size()-1; j>=0 ; j--) {
            child = n.getChildren().get(j);
            vChild = g.insertVertex(g.getDefaultParent(), null, child.getName(),0,0,30,30);
            g.insertEdge(g.getDefaultParent(), null,Integer.toString(n.production),vParent ,vChild);
            if (child instanceof NonTerminalNode) {
                generateGraph(g, vChild, (NonTerminalNode) child);
            }
        }

    }

    private mxGraph generateGraph(NonTerminalNode n) {
        mxGraph g = new mxGraph();
        g.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, true);
        g.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR, "#ff0000");
        g.getModel().beginUpdate();
        try {
            Object vRoot = g.insertVertex(g.getDefaultParent(), null, n.nt.name(),0,0,30,30);
            generateGraph(g, vRoot, n);
        } finally {
            g.getModel().endUpdate();
        }
        mxCompactTreeLayout layout = new mxCompactTreeLayout(g);
        layout.setHorizontal(false);
        layout.execute(g.getDefaultParent());
        return g;
    }
}
