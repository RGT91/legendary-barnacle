package ast.patron.compuesto;
import ast.patron.visitante.*;

public class DivENodo extends NodoBinario
{

    public DivENodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}