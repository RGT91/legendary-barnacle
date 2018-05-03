%{
  import java.lang.Math;
  import java.io.*;
%}
/* Átomos del lenguaje */
%token DEINDENTA INDENTA IDENTIFICADOR ENTERO CADENA REAL BOOLEANO POWER
%token SIGNUM OPERADOR COMPARADOR NOT AND OR LEFTPAR RIGTHPAR SALTO
%token WHILE SEPARADOR IF ELSE PRINT EQ

/* Producciones */
%%
input:  { System.out.println("Reconocimiento Exitoso");}
     | file_input { System.out.println("Reconocimiento Exitoso");}
;
// file_input: (SALTO | stmt)*
file_input: SALTO                   { System.out.println("[OK]");}
  | stmt                   { System.out.println("[OK]");}
  | SALTO file_input                { System.out.println("[OK]");}
  | stmt file_input                   { System.out.println("[OK]");}
  ;
// stmt: simple_stmt | compound_stmt
stmt: simple_stmt
  | compound_stmt
;
// simple_stmt: small_stmt SALTO
simple_stmt: small_stmt SALTO
;
// small_stmt: expr_stmt | print_stmt
small_stmt: expr_stmt
  | print_stmt
;
// expr_stmt: test '=' test
expr_stmt: test
  | test EQ test
;
//print_stmt: 'print' test
print_stmt: PRINT test
;
// compound_stmt: if_stmt | while_stmt
compound_stmt: if_stmt
  | while_stmt
;
// if_stmt: 'if' test ':' suite ['else' ':' suite]
if_stmt: IF test SEPARADOR suite
  | IF test SEPARADOR suite ELSE SEPARADOR suite
;
// while_stmt: 'while' test ':' suite
while_stmt: WHILE test SEPARADOR suite
;
// suite: simple_stmt | SALTO INDENTA stmt+ DEINDENTA
suite: simple_stmt
  | SALTO INDENTA file_input DEINDENTA
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
