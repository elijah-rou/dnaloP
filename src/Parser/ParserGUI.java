import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

/**
 * Created by Neville Varney-Horwitz on 2018-03-20.
 */
public class ParserGUI extends JFrame {
    static Terminal[][] sampleInputs = new Terminal[][]{
       {
            Terminal.BOF,
            Terminal.OPEN_CURLY,
            Terminal.LITERAL, 
            Terminal.LITERAL, 
            Terminal.OPERATION, 
            Terminal.LITERAL, 
            Terminal.OPERATION, 
            Terminal.IDENTIFIER, 
            Terminal.ASSIGN, 
            Terminal.SEMICOLON,
            Terminal.CLOSE_CURLY
        },
        {
            Terminal.BOF, //beginning of file
            Terminal.OPEN_CURLY,
            Terminal.LITERAL, Terminal.IDENTIFIER, Terminal.ASSIGN, Terminal.SEMICOLON,
            Terminal.IDENTIFIER, Terminal.LITERAL, Terminal.OPERATION,
            Terminal.OPEN_CURLY,
            Terminal.IDENTIFIER, Terminal.LITERAL, Terminal.OPERATION, Terminal.IDENTIFIER, Terminal.ASSIGN, Terminal.SEMICOLON,
            Terminal.CLOSE_CURLY,
            Terminal.OPEN_CURLY, Terminal.CLOSE_CURLY,
            Terminal.IF, Terminal.SEMICOLON,
            Terminal.CLOSE_CURLY
        }
    };

    public static void main(String[] args) throws ParseException{
        danlop parser;

        if(args.length == 0){
            System.out.println ("danlop: Reading input ...");
            parser = new danlop(System.in);
        }
        else if(args.length == 1){
            System.out.println ("danlop: Reading the file " + args[0] + " ..." );
            try {
            parser = new danlop(new java.io.FileInputStream(args[0]));
            }
            catch(java.io.FileNotFoundException e) {
            System.out.println ("danlop: The file " + args[0] + " was not found.");
            return;
            }
        }
        else {
        System.out.println ("danlop:  You must use one of the following:");
        System.out.println ("         java danlop < file");
        System.out.println ("Or");
        System.out.println ("         java danlop file");
        return ;
        }
        try
        {
            // Convert this method into tokens for parser
            parser.Test();
            System.out.println ("danlop: The input was read sucessfully.");
        }
        catch(ParseException e){
            System.out.println ("danlop: There was an error during the parse.");
            System.out.println (e.getMessage());
        }
        catch(TokenMgrError e){
            System.out.println ("danlop: There was an error.");
            System.out.println (e.getMessage());
        }

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
