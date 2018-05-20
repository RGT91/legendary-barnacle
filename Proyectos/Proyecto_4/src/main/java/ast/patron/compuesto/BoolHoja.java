package ast.patron.compuesto;
import ast.patron.visitante.*;

public class BoolHoja extends Hoja
{
    public BoolHoja(boolean b){
	     valor = new Variable(b);
	     tipo = 3;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
