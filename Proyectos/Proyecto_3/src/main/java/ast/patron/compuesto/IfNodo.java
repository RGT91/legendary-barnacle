package ast.patron.compuesto;
import ast.patron.visitante.*;

public class IfNodo extends NodoTernario
{

    public IfNodo(Nodo l, Nodo r){
	     super(l,r);
    }

    public void accept(Visitor v){
	     v.visit(this);
    }
}
