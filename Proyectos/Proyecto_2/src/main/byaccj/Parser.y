%{
  import java.lang.Math;
  import java.io.*;
%}
/* Átomos del lenguaje */
%token DEINDENTA INDENTA OTRO IDENTIFICADOR ENTERO CADENA REAL BOOLEANO POWER
%token SIGNUM OPERADOR COMPARADOR NOT AND OR LEFTPAR RIGTHPAR SALTO

/* Producciones */
%%
input:  { System.out.println("Reconocimiento Exitoso");}
     | indenta { System.out.println("Reconocimiento Exitoso");}
;
indenta : file_input
        | file_input INDENTA indenta DEINDENTA
        | file_input INDENTA indenta DEINDENTA indenta
;
// file_input: (SALTO | stmt)*
file_input: SALTO                   { System.out.println("[OK]");}
  | stmt                   { System.out.println("[OK]");}
  | SALTO file_input                { System.out.println("[OK]");}
  | stmt file_input                   { System.out.println("[OK]");}
  ;
/* stmt: OTRO+ */
stmt: test
    | stmt test
;
// test: or_test
test: or_test                   { System.out.println("[OK]");}
  ;
// or_test: and_test ('or' and_test)*
or_test: and_test                   { System.out.println("[OK]");}
  | and_test or_rec                   { System.out.println("[OK]");}
  ;

or_rec: OR or_test               { System.out.println("[OK]");}
  ;
// and_test: not_test ('and' not_test)*
and_test: not_test                   { System.out.println("[OK]");}
  | not_test and_rec                   { System.out.println("[OK]");}
  ;

and_rec: AND and_test               { System.out.println("[OK]");}
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
  | LEFTPAR test RIGTHPAR
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
