/********************************************************************************
**  @author Diana Montes                                               	       **
**  @about Proyecto 2: Analizador léxico para reconocer indentación.	       **
*********************************************************************************/
package asintactico;
import java.util.Stack;
import java.io.IOException;
%%
%public
%class Flexer
%byaccj
%line
%debug
%state INDENTA CODIGO DEINDENTA CADENA
%unicode
%{
    /** Variables auxiliares para
    * manejar la indentación.*/
    static Stack<Integer> pila = new Stack<Integer>();
    static Integer actual = 0;
    static String cadena = "";
    static int dedents = 0;
    static int indents = 0;

    private Parser yyparser;

    public int line(){
        return yyline+1;
    }

    /** Nuevo constructor
    * @param FileReader r
    * @param Parser parser
    */
    public Flexer(java.io.Reader r, Parser parser){
    	   this(r);
    	   yyparser = parser;
    }

    /** Función que maneja los niveles de indetación
    * @param int espacios - nivel de indetación actual.
    * @return void
    */
    public void indentacion(int espacios){
        if(pila.empty()){ //ponerle un cero a la pila si esta vacia
             pila.push(new Integer(0));
        }

        Integer tope = pila.peek();

        if(tope != espacios){
	    //Se debe emitir un DEINDENTA por cada nivel mayor al actual
            if(tope > espacios){
                while(pila.peek() > espacios &&  pila.peek()!=0 ){
                    pila.pop();
                    dedents += 1;
                }
                if(pila.peek() == espacios){
		    yybegin(DEINDENTA);
                }else{
		    System.out.println("Error de indentación. Línea "+(yyline+1));
		    System.exit(1);
		}
                return;
            }
   	    //El nivel actual de indentación es mayor a los anteriores.
            pila.push(espacios);
	    yybegin(CODIGO);
            indents = 1;
        }else yybegin(CODIGO);
    }
%}
SALTO           =       (\r|\n|\r\n)+
CHAR_LITERAL   	= ([:letter:] | [:digit:] | "_" | "$" | " " | "#" | {OPERADOR} | {COMPARADOR} | {SEPARADOR}) | "\\" | "\\\""
BOOLEANO        = "True" | "False"
LEFTPAR         = "("
RIGTHPAR        = ")"
SEPARADOR       = ":"
EQ              = "="
OR              = "or"
IF              = "if"
AND             = "and"
WHILE           = "while"
PRINT           = "print"
NOT             = "not" | "!"
SIGNUM          = "+" | "-"
OPERADOR        = "*" | "/" | "%" | "//"
COMPARADOR      = "<" | ">" | "==" | ">=" | "<=" | "!="
POWER           = "**"
ASCIIDIGIT      = [0-9]
DIGIT           = {ASCIIDIGIT}
OCTIT           = [0-7]
HEXIT           = {DIGIT} | [A-Fa-f]
DECIMAL         = {DIGIT}+
OCTAL           = {OCTIT}+
HEXADECIMAL     = {HEXIT}+
ENTERO          = {DECIMAL} | "0o" {OCTAL} | "0O" {OCTAL} | "0x" {HEXADECIMAL} | "0X" {HEXADECIMAL}
REAL            = {DECIMAL} "." {DECIMAL} {EXPONENTE}? | {DECIMAL} {EXPONENTE}?
EXPONENTE       = ("e" | "E") {SIGNUM}? {DECIMAL}
IDENTIFICADOR   = [a-zA-Z_]([a-zA-Z0-9_])*
ENDMARKER	= <<EOF>>
COMENTARIO 		=     	"#".*{SALTO}
%%
{COMENTARIO}      			{}
<YYINITIAL>{
  (" " | "\t" )+[^" ""\t""#""\n"]         { System.out.println("Error de indentación. Línea "+(yyline+1));
					    System.exit(1);}
  {SALTO}                                 {}
  [^" ""\t"]                              { yypushback(1); yybegin(CODIGO);}
}
<DEINDENTA>{
  .                                       { yypushback(1);
  					    if(dedents > 0){
						dedents--;
						return Parser.DEINDENTA;
  					    }
					    yybegin(CODIGO);}
}
<CADENA>{
  {CHAR_LITERAL}*(\" | ' )			{ yybegin(CODIGO); return Parser.CADENA; }
  {SALTO}				{ return 0;}
}
<CODIGO>{
  \"					{ yybegin(CADENA); }
  '				{ yybegin(CADENA); }
  {SALTO}				  { yybegin(INDENTA); actual=0; return Parser.SALTO;}
  {POWER}      {  return Parser.POWER; }
  {LEFTPAR}      {  return Parser.LEFTPAR; }
  {RIGTHPAR}      {  return Parser.RIGTHPAR; }
  {SEPARADOR}      {  return Parser.SEPARADOR; }
  {EQ}      {  return Parser.EQ; }
  {OR}      {  return Parser.OR; }
  {AND}      {  return Parser.AND; }
  {NOT}      {  return Parser.NOT; }
  {IF}      {  return Parser.IF; }
  {WHILE}      {  return Parser.WHILE; }
  {PRINT}      {  return Parser.PRINT; }
  {SIGNUM}      {  return Parser.SIGNUM; }
  {OPERADOR}      {  return Parser.OPERADOR; }
  {COMPARADOR}      {  return Parser.COMPARADOR; }
  {BOOLEANO}      {  return Parser.BOOLEANO; }
  {ENTERO}      { return Parser.ENTERO; }
  {REAL}      { return Parser.REAL; }
  {IDENTIFICADOR}      { return Parser.IDENTIFICADOR; }
}
<INDENTA>{
  " "				          { actual++;}
  \t					  { actual += 4;}
  .					  { yypushback(1);
					    this.indentacion(actual);
					    if(indents == 1){
					      indents = 0;
					      return Parser.INDENTA;
					    }
					  }
}
<<EOF>>                                   { this.indentacion(0);
					    if(dedents > 0){
					      dedents--;
					      return Parser.DEINDENTA;
					    }else{
                                              return 0;
				            }
					  }
" "         { }
.					  { throw new IOException("no reconocido");}
