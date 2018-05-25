package ast.patron.compuesto;
import ast.patron.visitante.*;

public class CompNodo extends NodoBinario
{
    public String op;

    public CompNodo(String op){
	    super(null,null);
      this.op = op;
    }

    public void accept(Visitor v){
	v.visit(this);
    }
}
