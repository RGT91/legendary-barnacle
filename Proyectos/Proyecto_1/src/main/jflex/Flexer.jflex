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
    tokens += "INDENT("+cont+")";
  }else if (cont<(int)st.peek()){
    while(cont<(int)st.peek()&&(int)st.peek()!=0){
      st.pop();
    }
    if((int)st.peek()==0&&cont!=0){
      tokens+="\nError de identación"; 
      System.exit(0);
    }
    tokens+="DEINDENT("+cont+")\n";
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
  {SALTO}     { tokens += "SALTO\n"; yybegin(INDENT); cont=0; }
  {STRING}      { tokens += "STRING("+yytext() + ") "; }
  {OPERADOR}      { tokens += "OPERADOR("+yytext() + ") "; }
  {SEPARADOR}      { tokens += "SEPARADOR("+yytext() + ") "; }
  {BOOLEANO}      { tokens += "BOOLEANO("+yytext() + ") "; }
  {ENTERO}      { tokens += "ENTERO("+yytext() + ") "; }
  {FLOTANTE}      { tokens += "FLOTANTE("+yytext() + ") "; }
  {RESERVADA}      { tokens += "RESERVADA("+yytext() + ") "; }
  {IDENTIFICADOR}      { tokens += "IDENTIFICADOR("+yytext() + ") "; }
}

<INDENT> {
  {SALTO}     { }
  [ ]         {  cont++;}
  [\t\f]         {  cont+=4;}
  {STRING}      { tokens+=checkStack(st,cont); tokens += "STRING("+yytext() + ") ";  yybegin(YYINITIAL);}
  {OPERADOR}      { tokens+=checkStack(st,cont); tokens += "OPERADOR("+yytext() + ") "; yybegin(YYINITIAL);}
  {SEPARADOR}      { tokens+=checkStack(st,cont);  tokens += "SEPARADOR("+yytext() + ") "; yybegin(YYINITIAL);}
  {BOOLEANO}      { tokens+=checkStack(st,cont);  tokens += "BOOLEANO("+yytext() + ") ";yybegin(YYINITIAL); }
  {ENTERO}      { tokens+=checkStack(st,cont);  tokens += "ENTERO("+yytext() + ") ";yybegin(YYINITIAL); }
  {FLOTANTE}      { tokens+=checkStack(st,cont);  tokens += "FLOTANTE("+yytext() + ") ";yybegin(YYINITIAL); }
  {RESERVADA}      { tokens+=checkStack(st,cont);  tokens += "RESERVADA("+yytext() + ") ";yybegin(YYINITIAL); }
  {IDENTIFICADOR}      { tokens+=checkStack(st,cont);  tokens += "IDENTIFICADOR("+yytext() + ") "; yybegin(YYINITIAL);}
}
.             { tokens += "Caracter ilegal <"+yytext()+">. En la linea "+ (yyline+1)+ ".\n"; return 0; }
