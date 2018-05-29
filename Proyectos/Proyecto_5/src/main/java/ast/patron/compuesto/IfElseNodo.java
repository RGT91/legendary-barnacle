package ast.patron.compuesto;
import ast.patron.visitante.*;

public class IfElseNodo extends NodoTernario
{

    public IfElseNodo(Nodo l, Nodo c, Nodo r){
	     super(l,c,r);
    }

    public void accept(Visitor v){
	     v.visit(this);
    }
}
