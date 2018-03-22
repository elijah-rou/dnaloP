/* Generated By:JavaCC: Do not edit this line. danlop.java */
import java.util.*;
import java.io.*;
/** Reverse polish notation lexer */
public class danlop implements danlopConstants {
        
  static final public void Test() throws ParseException {
        String s;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER:
      case FLOAT:
      case IF:
      case WHILE:
      case OPEN_CURLY:
      case CLOSE_CURLY:
      case SEMICOLON:
      case OPEN_PAREN:
      case CLOSE_PAREN:
      case BOOLEAN:
      case IDENTIFIER:
      case STRING:
      case LIST:
      case OPERATOR:
      case ASSIGN:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      s = value();
                        try
                        {
                                BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt",true));
                                writer.write(s);
                                writer.close();
                        }
                        catch(IOException e)
                        {}
    }
    jj_consume_token(0);
  }

  static final public String value() throws ParseException {
        Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      t = jj_consume_token(INTEGER);
                {if (true) return "<LITERAL"+","+t.image+">"+"\u005cn";}
      break;
    case FLOAT:
      t = jj_consume_token(FLOAT);
                {if (true) return "<LITERAL"+","+t.image+">"+"\u005cn";}
      break;
    case STRING:
      t = jj_consume_token(STRING);
                {if (true) return "<LITERAL"+","+t.image+">"+"\u005cn";}
      break;
    case IDENTIFIER:
      t = jj_consume_token(IDENTIFIER);
                {if (true) return "<LITERAL"+","+t.image+">"+"\u005cn";}
      break;
    case LIST:
      t = jj_consume_token(LIST);
                {if (true) return "<LITERAL"+","+t.image+">"+"\u005cn";}
      break;
    case BOOLEAN:
      t = jj_consume_token(BOOLEAN);
                {if (true) return "<LITERAL"+","+t.image+">"+"\u005cn";}
      break;
    case IF:
      t = jj_consume_token(IF);
                {if (true) return "<IF"+","+t.image+">"+"\u005cn";}
      break;
    case WHILE:
      t = jj_consume_token(WHILE);
                {if (true) return "<WHILE"+","+t.image+">"+"\u005cn";}
      break;
    case OPEN_CURLY:
      t = jj_consume_token(OPEN_CURLY);
                {if (true) return "<OPEN_CURLY"+","+t.image+">"+"\u005cn";}
      break;
    case CLOSE_CURLY:
      t = jj_consume_token(CLOSE_CURLY);
                {if (true) return "<CLOSE_CURLY"+","+t.image+">"+"\u005cn";}
      break;
    case SEMICOLON:
      t = jj_consume_token(SEMICOLON);
                {if (true) return "<SEMICOLON"+","+t.image+">"+"\u005cn";}
      break;
    case OPEN_PAREN:
      t = jj_consume_token(OPEN_PAREN);
                {if (true) return "<OPEN_PAREN"+","+t.image+">"+"\u005cn";}
      break;
    case CLOSE_PAREN:
      t = jj_consume_token(CLOSE_PAREN);
                {if (true) return "<CLOSE_PAREN"+","+t.image+">"+"\u005cn";}
      break;
    case OPERATOR:
      t = jj_consume_token(OPERATOR);
                {if (true) return "<OPERATOR"+","+t.image+">"+"\u005cn";}
      break;
    case ASSIGN:
      t = jj_consume_token(ASSIGN);
                {if (true) return "<ASSIGN"+","+t.image+">"+"\u005cn";}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public danlopTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x77ff80,0x77ff80,};
   }

  /** Constructor with InputStream. */
  public danlop(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public danlop(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new danlopTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public danlop(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new danlopTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public danlop(danlopTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(danlopTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[24];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 24; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

        }
