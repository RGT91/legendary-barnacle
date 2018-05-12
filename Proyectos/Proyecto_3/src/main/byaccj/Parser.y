%{
  import ast.patron.compuesto.*;
  import java.lang.Math;
  import java.io.*;
%}
/* Átomos del lenguaje */
%token CADENA
%token SALTO IDENTIFICADOR ENTERO REAL
%token BOOLEANO DEINDENTA INDENTA
%token AND OR NOT WHILE
%token FOR PRINT ELIF ELSE IF
%token MAS MENOS POR POTENCIA DIV  /* + | - | * | ** | / */
%token DIVENTERA MODULO LE GR LEQ /* // | % | < | > | <= */
%token GRQ EQUALS DIFF EQ PA /* >= | == | != | = | ( */
%token PC DOBLEPUNTO /* ) | : | ; */

/* Producciones */
%%
/*    input: (SALTO | stmt)* ENDMARKER */
input:      {raíz = $$; System.out.println("Reconocimiento Exitoso");}
     | aux0 {raíz = $1; System.out.println("Reconocimiento Exitoso");}
;

/*    aux0: (SALTO | stmt)+ */
aux0: SALTO
    | stmt {$$ = new NodoStmts($1);}
    | aux0 SALTO {$$ = $1;}
    | aux0 stmt {$1.agregaHijoFinal($2); $$ = $1;}
;

/*    stmt: simple_stmt | compound_stmt*/
stmt: simple_stmt {$$ = $1;}
    | compound_stmt { $$ = $1; }
;

/* compound_stmt: if_stmt | while_stmt */
compound_stmt: if_stmt { }
             | while_stmt { $$ = $1;}
;

/* if_stmt: 'if' test ':' suite ['else' ':' suite] */
if_stmt:  IF test DOBLEPUNTO suite ELSE DOBLEPUNTO suite { $$ = new IfElseNodo($2, $4, $7); }
        | IF test DOBLEPUNTO suite { $$ = new IfNodo($2, $4); }
;

/*    while_stmt: 'while' test ':' suite */
while_stmt: WHILE test DOBLEPUNTO suite {$$ = new WhileNodo($2, $4);}
;

/*    suite: simple_stmt | SALTO INDENTA stmt+ DEINDENTA */
suite: simple_stmt {$$ = $1;}
     | SALTO INDENTA auxstmt DEINDENTA {$$ = $3;}
;

/*    auxstmt:  stmt+ */
auxstmt: stmt {$$ = new NodoStmts($1);}
       | auxstmt stmt {$1.agregaHijoFinal($2); $$ = $1;}
;

/* simple_stmt: small_stmt SALTO */
simple_stmt: small_stmt SALTO {$$ = $1;}
;

/* small_stmt: expr_stmt | print_stmt  */
small_stmt: expr_stmt {$$ = $1;}
          | print_stmt { $$ = $1;}
;

/* expr_stmt: test ['=' test] */
expr_stmt: test {$$ = $1;}
         | test EQ test { $$ = new AsigNodo($1, $3); }
;

/* print_stmt: 'print' test  */
print_stmt: PRINT test { $$ = new PrintNodo($2); }
;

/*   test: or_test */
test: or_test {$$ = $1;}
;

/*    or_test: (and_test 'or')* and_test  */
or_test: and_test {$$ = $1;}
       | aux2 and_test {$$ = $1; $$.agregaHijoFinal($2);}
;
/*    aux2: (and_test 'or')+  */
aux2: and_test OR {$$ = $1;}
    | aux2 and_test OR {$$ = new OrNodo($1, $3);}
;

/*    and_expr: (not_test 'and')* not_test */
and_test: not_test {$$ = $1;}
        | aux7 not_test {$$ = $1; $$.agregaHijoFinal($2);}
;

/*    and_expr: (not_test 'and')+ */
aux7: not_test AND {$$ = $1;}
    | aux7 not_test AND {$$ = new AndNodo($1, $3);}
;

/*    not_test: 'not' not_test | comparison */
not_test: NOT not_test {$$ = new OrNodo($1, null);}
        | comparison {$$ = $1;}
;

/*    comparison: (expr comp_op)* expr  */
comparison: expr {$$ = $1;}
          | aux4 expr { $$ = $1; $$.agregaHijoFinal($2); }
;

/*    aux4: (expr comp_op)+  */
aux4: expr comp_op {$$ = $2; $$.agregaHijoPrincipio($1); }
    | aux4 expr comp_op { $$ = $3; $1.agregaHijoFinal($2); $$.agregaHijoPrincipio($1); }
;

/*    comp_op: '<'|'>'|'=='|'>='|'<='|'!=' */
comp_op: LE {}
       | GR {}
       | EQUALS {}
       | GRQ {}
       | LEQ {}
       | DIFF {}
;

/*    expr: (term ('+'|'-'))* term   */
expr: term {$$ = $1;}
    | aux8 term {$$ = $1; $$.agregaHijoFinal($2);}
;
aux8: term MAS {$$ = new AddNodo($1,null);}
    | term MENOS {$$ = new DifNodo($1,null);}
    | aux8 term MAS {$1.agregaHijoFinal($2); $$ = new AddNodo($1,null);}
    | aux8 term MENOS {$1.agregaHijoFinal($2); $$ = new DifNodo($1,null);}
;

/*   term: (factor ('*'|'/'|'%'|'//'))* factor   */
term: factor {$$ = $1;}
    | aux9 factor {$$ = $1; $$.agregaHijoFinal($2);}
;
aux9: factor POR {$$ = new MultNodo($1,null);}
    | factor DIVENTERA {$$ = new DivENodo($1,null);}
    | factor MODULO {$$ = new ModNodo($1,null);}
    | factor DIV {$$ = new DivNodo($1,null);}
    | aux9 factor POR {$1.agregaHijoFinal($2); $$ = new MultNodo($1,null);}
    | aux9 factor DIVENTERA {$1.agregaHijoFinal($2); $$ = new DivENodo($1,null);}
    | aux9 factor MODULO {$1.agregaHijoFinal($2); $$ = new ModNodo($1,null);}
    | aux9 factor DIV {$1.agregaHijoFinal($2); $$ = new DivNodo($1,null);}
;
/* factor: ('+'|'-') factor | power */
factor: MAS factor {}
      | MENOS factor {}
      | power {$$ = $1;}
;
/* power: atom ['**' factor] */
power:  atom {$$ = $1;}
      | atom POTENCIA factor {$$ = new PotNodo($1, $3);}
;

/* atom: IDENTIFICADOR | ENTERO | CADENA | REAL | BOOLEANO | '(' test ')' */
atom:  IDENTIFICADOR {$$ = $1;}
     | ENTERO {$$ = $1;}
     | CADENA {}
     | REAL {}
     | BOOLEANO {$$ = $1;}
     | PA test PC {}
;
%%
private Flexer lexer;
/* Nodo Raiz del AST */
public Nodo raíz;

/* Comunicación con el analizador léxico */
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

/* Reporta errores y para ejecución. */
public void yyerror (String error) {
   System.err.println ("Error sintáctico en la línea " + lexer.line());
   System.exit(1);
}

/* lexer es creado en el constructor. */
public Parser(Reader r) {
    lexer = new Flexer(r, this);
    yydebug = true;
}
