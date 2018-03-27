import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

import java.util.ArrayList;

/**
 * Created by Neville Varney-Horwitz on 2018-03-20.
 */
public class ParserGUI extends JFrame {
    
    static ArrayList<TerminalPair> terminals = new ArrayList<TerminalPair>();
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
            terminals = parser.Lex();
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
        // TEST
        for(TerminalPair t : terminals){
          System.out.println(t.toString());
        }
        Parser parser = new Parser(terminals);
        //generateStack();
        mxGraph g = generateGraph(parser.getParseTree());
        mxGraphComponent graphComponent = new mxGraphComponent(g);
        getContentPane().add(graphComponent);
    }

    private void generateGraph(mxGraph g, Object vParent, NonTerminalNode n) {

        Object vChild;
        ParseNode child;
        for (int j = n.getChildren().size()-1; j>=0 ; j--) {
            child = n.getChildren().get(j);
            vChild = g.insertVertex(g.getDefaultParent(), null, child.getName(),0,0,100,30);
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
            Object vRoot = g.insertVertex(g.getDefaultParent(), null, n.nt.name(),0,0,100,30);
            generateGraph(g, vRoot, n);
        } finally {
            g.getModel().endUpdate();
        }
        mxCompactTreeLayout layout = new mxCompactTreeLayout(g);
        layout.setHorizontal(false);
        layout.execute(g.getDefaultParent());
        return g;
    }

    // Courtesy of Dan
    /*
    public void generateStack(ArrayList<TerminalPair> terms){
        mxGraph s = new mxGraph();
        mxGraphComponent graphComponent = new mxGraphComponent(s);
        getContentPane().add(graphComponent);
        s.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, true);
        s.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR, "#ff0000");
        s.getModel().beginUpdate();
        try{
            Object vRoot= s.insertVertex(s.getDefaultParent(),null, terms.get(0).value ,0,0,30);
            Object vParent = vRoot;
            Object vChild;
            for (int i =0;i < terms.size(); i++ ) {
                System.out.println(terms.get(i).value);
                child=s.insertVertex(s.getDefaultParent( null, terms.get(i).value,0,0,30,30));
                s.insertEdge(s.getDefaultParent(), null, Integer.toString(i) ,vParent ,vChild);
                vParent=vchild;
            }
        }
        finally {
                s.getModel().endUpdate();
        } 
        }
        
        mxCompactTreeLayout layout = new mxCompactTreeLayout(s);
        layout.setHorizontal(false);
        layout.execute(s.getDefaultParent()); 
    } 
    */
}
