package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NotHoja extends Hoja
{

    public NotHoja(boolean b){
	val = !b;
    }

    public void accept(Visitor v){
	v.visit(this);
    }
}