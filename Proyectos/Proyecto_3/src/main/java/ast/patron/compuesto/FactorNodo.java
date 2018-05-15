package ast.patron.compuesto;
import ast.patron.visitante.*;

public class FactorNodo extends NodoBinario
{
    public FactorNodo(SignoHoja s, Nodo l){
        super(s, l);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
