package ast.patron.compuesto;
import ast.patron.visitante.*;

public class LENodo extends NodoBinario
{

    public LENodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}