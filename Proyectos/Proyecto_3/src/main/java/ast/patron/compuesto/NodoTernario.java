package ast.patron.compuesto;
import ast.patron.visitante.*;

public class NodoTernario extends Compuesto
{
    public NodoTernario(Nodo l, Nodo c, Nodo r){
	this.agregaHijoPrincipio(l);
  this.agregaHijoFinal(c);
	this.agregaHijoFinal(r);
    }

    public NodoTernario(Nodo l, Nodo c){
	this.agregaHijoPrincipio(l);
  this.agregaHijoFinal(c);
    }

    public NodoTernario(Nodo l){
	this.agregaHijoPrincipio(l);
    }

    public NodoTernario(){
	super();
    }

    public void accept(Visitor v){
     	v.visit(this);
    }
}
