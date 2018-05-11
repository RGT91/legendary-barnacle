package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NEqNodo extends NodoBinario
{

    public NEqNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}