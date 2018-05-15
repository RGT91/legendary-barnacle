package ast.patron.compuesto;
import ast.patron.visitante.*;

public class LTNodo extends NodoBinario
{

    public LTNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}