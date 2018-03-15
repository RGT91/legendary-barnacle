package lexico;

import java.util.Stack;

%%
%class Flexer
%public
%standalone
%line
%unicode
%{
public String checkStack(Stack st, int cont){
  if (cont>(int)st.peek()){
    st.push(cont);
    tokens += "INDENT";
  }else if (cont<(int)st.peek()){
    while(cont<(int)st.peek()&&(int)st.peek()!=0){
      st.pop();
    }
    if((int)st.peek()==0){
      return "error";
    }
    tokens+="DEINDENT";
  }else{
  }
  return tokens;
}
%}

%{
  protected int levels;
  protected int levels_aux;
  public String tokens;
  public int cont;
  public Stack st;
%}

%init{
  levels = 0;
  levels_aux = 0;
  tokens = "";
  cont=0;
  st = new Stack();
  st.push(0);
%init}


SALTO           = (\r|\n|\r\n)+
STRING          = ("\"" {STRINGCONT} "\"") | ("'" {STRINGCONT} "'")
STRINGCONT      = ( (\\\") | (\\\') | \? | [^\n\\\"\\\'?])*
SEPARADOR       = ":"
OPERADOR        = "+" | "*" | "**" | "-" | "/" | "//" | "%" | "<" | ">" | ">=" | "<=" | "==" | "=" | "!"
BOOLEANO        = "True" | "False"
ASCIIDIGIT      = [0-9]
DIGIT           = {ASCIIDIGIT}
OCTIT           = [0-7]
HEXIT           = {DIGIT} | [A-Fa-f]
DECIMAL         = {DIGIT}+
OCTAL           = {OCTIT}+
HEXADECIMAL     = {HEXIT}+
ENTERO          = {DECIMAL} | "0o" {OCTAL} | "0O" {OCTAL} | "0x" {HEXADECIMAL} | "0X" {HEXADECIMAL}
FLOTANTE        = {DECIMAL} "." {DECIMAL} {EXPONENTE}? | {DECIMAL} {EXPONENTE}?
EXPONENTE       = ("e" | "E") ("+" | "-")? {DECIMAL}
RESERVADA       = "if" | "else" | "elif" | "print" | "while" | "for" | "not" | "and" | "or"
IDENTIFICADOR   = [a-zA-Z_]([a-zA-Z0-9_])*

%state INDENT

%%
<YYINITIAL> {
  [ \t\f]         { if(yyline==0){ tokens += "Error de indentación. Línea 1.\n"; return 0; } }
  {SALTO}     { tokens = "SALTO\n"; yybegin(INDENT); cont=0; System.out.print(tokens);}
  {STRING}      { tokens = "STRING("+yytext() + ") "; System.out.print(tokens);System.out.print(tokens);}
  {OPERADOR}      { tokens = "OPERADOR("+yytext() + ") "; System.out.print(tokens);}
  {SEPARADOR}      { tokens = "SEPARADOR("+yytext() + ") "; System.out.print(tokens);}
  {BOOLEANO}      { tokens = "BOOLEANO("+yytext() + ") "; System.out.print(tokens);}
  {ENTERO}      { tokens = "ENTERO("+yytext() + ") "; System.out.print(tokens);}
  {FLOTANTE}      { tokens = "FLOTANTE("+yytext() + ") "; System.out.print(tokens);}
  {RESERVADA}      { tokens = "RESERVADA("+yytext() + ") "; System.out.print(tokens);}
  {IDENTIFICADOR}      { tokens = "IDENTIFICADOR("+yytext() + ") "; System.out.print(tokens);}
}

<INDENT> {
  {SALTO}     { }
  [ ]         {  cont++;}
  [\t\f]         {  cont+=11;}
  {STRING}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "STRING("+yytext() + ") ";  yybegin(YYINITIAL);System.out.print(tokens);}
  {OPERADOR}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "OPERADOR("+yytext() + ") "; yybegin(YYINITIAL);System.out.print(tokens);}
  {SEPARADOR}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "SEPARADOR("+yytext() + ") "; yybegin(YYINITIAL);System.out.print(tokens);}
  {BOOLEANO}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "BOOLEANO("+yytext() + ") ";yybegin(YYINITIAL); System.out.print(tokens);}
  {ENTERO}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "ENTERO("+yytext() + ") ";yybegin(YYINITIAL); System.out.print(tokens);}
  {FLOTANTE}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "FLOTANTE("+yytext() + ") ";yybegin(YYINITIAL); System.out.print(tokens);}
  {RESERVADA}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "RESERVADA("+yytext() + ") ";yybegin(YYINITIAL); System.out.print(tokens);}
  {IDENTIFICADOR}      { tokens=checkStack(st,cont); System.out.print(tokens); tokens = "IDENTIFICADOR("+yytext() + ") "; yybegin(YYINITIAL);System.out.print(tokens);}
}
.             { tokens += "Caracter ilegal <"+yytext()+">. En la linea "+ (yyline+1)+ ".\n"; return 0; }
