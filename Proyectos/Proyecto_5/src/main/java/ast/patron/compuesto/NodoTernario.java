package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NodoTernario extends Compuesto
{
    public NodoTernario(Nodo l, Nodo c, Nodo r){
	this.agregaHijoPrincipio(l);
  this.agregaHijoFinal(c);
	this.agregaHijoFinal(r);
    }

    public Nodo getIntermedio(){
      return getHijos().getSegundo();
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
