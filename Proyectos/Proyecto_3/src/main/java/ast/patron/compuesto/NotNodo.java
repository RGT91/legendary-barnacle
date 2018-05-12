package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NotNodo extends NodoBinario
{

    public NotNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
	v.visit(this);
    }
}
