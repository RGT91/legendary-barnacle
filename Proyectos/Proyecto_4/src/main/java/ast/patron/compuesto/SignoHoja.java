package ast.patron.compuesto;
import ast.patron.visitante.*;

public class SignoHoja extends Hoja
{
    public SignoHoja(String s){
	valor = new Variable(s);
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
