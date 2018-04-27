%{
  import java.lang.Math;
  import java.io.*;
%}
/* Átomos del lenguaje */
%token DEINDENTA INDENTA OTRO

/* Producciones */
%%
input:  { System.out.println("Reconocimiento Exitoso");}
     | indenta { System.out.println("Reconocimiento Exitoso");}
;
indenta : stmt
        | stmt INDENTA indenta DEINDENTA
        | stmt INDENTA indenta DEINDENTA indenta
;
/* stmt: OTRO+ */
stmt: OTRO
    | stmt OTRO
;
%%
/* referencia al generadores de analizadores léxicos */
private Flexer lexer;


private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
}


public void yyerror (String error) {
   System.err.println ("[ERROR]  " + error);
   System.exit(1);
}

/* lexer es creado en el constructor */
public Parser(Reader r) {
    lexer = new Flexer(r, this);
    //yydebug = true;
}

/* Uso del parser */
/* Creación del parser e inicialización del reconocimiento */
public static void main(String args[]) throws IOException {
  if(args.length!=0){
    Parser parser = new Parser(new FileReader(args[0]));
    parser.yydebug = true;
    parser.yyparse();
  }else{
    throw new IOException("without args");
  }
}
