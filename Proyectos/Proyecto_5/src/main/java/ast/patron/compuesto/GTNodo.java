package ast.patron.compuesto;
import ast.patron.visitante.*;

public class GTNodo extends NodoBinario
{

    public GTNodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}