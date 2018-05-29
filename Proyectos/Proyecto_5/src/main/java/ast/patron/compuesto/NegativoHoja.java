package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NegativoHoja extends SignoHoja
{
    public NegativoHoja(){
	     super("-");
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
