package ast.patron.compuesto;
import ast.patron.visitante.*;

public class PositivoHoja extends SignoHoja
{
    public PositivoHoja(){
	     super("+");
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
