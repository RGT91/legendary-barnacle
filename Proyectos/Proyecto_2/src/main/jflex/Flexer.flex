/********************************************************************************
**  @author Diana Montes                                               	       **
**  @about Proyecto 2: Analizador léxico para reconocer indentación.	       **
*********************************************************************************/
package asintactico;
import java.util.Stack;
%%
%public
%class Flexer
%byaccj
%line
%debug
%state INDENTA CODIGO DEINDENTA
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
SALTO                   =       "\n"
%%
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
<CODIGO>{
  {SALTO}				  { yybegin(INDENTA); actual=0;}
}
<INDENTA>{
  {SALTO}                                 { actual = 0;}
  " "				          { actual++;}
  \t					  { actual += 4;}
  .					  { yypushback(1);
					    this.indentacion(actual);
              System.out.println(indents);
					    if(indents == 1){
					      indents = 0;
                System.out.println("return identa");
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
.					  { return Parser.OTRO;}
