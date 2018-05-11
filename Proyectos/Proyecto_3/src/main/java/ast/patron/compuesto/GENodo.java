package ast.patron.compuesto;
import ast.patron.visitante.*;

public class GENodo extends NodoBinario
{

    public GENodo(Nodo l, Nodo r){
    public GENodo(Nodo l, Nodo r){
	super(l,r);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}