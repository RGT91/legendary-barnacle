package ast.patron.compuesto;
import ast.patron.visitante.*;

public class CadenaHoja extends Hoja
{
    public CadenaHoja(String s){
	valor = new Variable(s);
	tipo = 4;
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
