package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NotNodo extends NodoBinario
{

    public NotNodo(Nodo l){
	super(l, null);
    }

    public void accept(Visitor v){
	v.visit(this);
    }
}
