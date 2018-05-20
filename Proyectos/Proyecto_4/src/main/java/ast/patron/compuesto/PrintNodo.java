package ast.patron.compuesto;
import ast.patron.visitante.*;

public class PrintNodo extends NodoBinario
{

    public PrintNodo(Nodo l){
	     super(l,null);
    }

    public void accept(Visitor v){
	     v.visit(this);
    }
}
