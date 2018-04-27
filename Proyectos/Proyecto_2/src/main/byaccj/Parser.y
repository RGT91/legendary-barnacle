%{
  import java.lang.Math;
  import java.io.*;
%}
/* Átomos del lenguaje */
%token DEINDENTA INDENTA OTRO IDENTIFICADOR ENTERO CADENA REAL BOOLEANO POWER
%token SIGNUM OPERADOR COMPARADOR NOT

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
stmt: not_test
    | stmt not_test
;
// not_test: 'not' not_test | comparison
not_test: comparison                   { System.out.println("[OK]");}
  | NOT not_test                   { System.out.println("[OK]");}
  ;
// comparison: expr (comp_op expr)*
// comp_op: '<'|'>'|'=='|'>='|'<='|'!='
comparison: expr                   { System.out.println("[OK]");}
  | expr COMPARADOR comparison                   { System.out.println("[OK]");}
  ;
//expr: term (('+'|'-') term)*
expr: term                   { System.out.println("[OK]");}
  | term SIGNUM expr                   { System.out.println("[OK]");}
  ;
// term: factor (('*'|'/'|'%'|'//') factor)*
term: factor                   { System.out.println("[OK]");}
  | factor OPERADOR term                   { System.out.println("[OK]");}
  ;
// factor: ('+'|'-') factor | power
factor: power                   { System.out.println("[OK]");}
  | SIGNUM factor                   { System.out.println("[OK]");}
  ;

// power: atom ['**' factor]
power: atom                   { System.out.println("[OK]");}
  | atom POWER factor                   { System.out.println("[OK]");}
  ;
// atom: IDENTIFICADOR | ENTERO | CADENA | REAL | BOOLEANO | '(' test ')'
atom: IDENTIFICADOR
  | ENTERO
  | CADENA
  | REAL
  | BOOLEANO
  | OTRO
  ;
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
