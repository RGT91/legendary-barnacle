package ast.patron.compuesto;
import ast.patron.visitante.*;

public class FloatHoja extends Hoja
{
    public FloatHoja(double d){
	valor = new Variable(d);
	tipo = 2;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
