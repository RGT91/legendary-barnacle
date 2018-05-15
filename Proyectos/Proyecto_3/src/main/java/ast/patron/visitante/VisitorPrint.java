package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.LinkedList;
import java.util.Iterator;

public class VisitorPrint implements Visitor
{

    public void visit(AddNodo n){
        System.out.println("[+]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }
    public void visit(AsigNodo n){
        System.out.println("[=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");

    }
    public void visit(Compuesto n){
        for (Iterator i = n.getHijos().iterator(); i.hasNext(); ) {
            Nodo hijo = (Nodo) i.next();
            System.out.print("[");
            hijo.accept(this);
            System.out.println("]");
        }

    }
    public void visit(DifNodo n){
        System.out.println("[-]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(CompNodo n){
        System.out.println("["+n.op+"]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(WhileNodo n){
        System.out.println("[while]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][:]\n[");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(IfNodo n){
        System.out.println("[if]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][:]\n[");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(IfElseNodo n){
        System.out.println("[if]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][:]\n[");
        n.getUltimoHijo().accept(this);
        System.out.print("][else][:]\n[");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(PrintNodo n){
        System.out.println("[print]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
    }
    public void visit(MultNodo n){
        System.out.println("[*]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }
    public void visit(DivNodo n){
        System.out.println("[/]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(PotNodo n){
        System.out.println("[**]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }
    public void visit(LTNodo n){
        System.out.println("[<]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(GTNodo n){
        System.out.println("[>]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(LENodo n){
        System.out.println("[<=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(GENodo n){
        System.out.println("[>=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(EqNodo n){
        System.out.println("[==]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(NEqNodo n){
        System.out.println("[!=]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(ModNodo n){
        System.out.println("[%]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }

    public void visit(DivENodo n){
        System.out.println("[//]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.println("]");
    }
    public void visit(AndNodo n){
        System.out.println("[&]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][:]\n[");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(OrNodo n){
        System.out.println("[|]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("][:]\n[");
        n.getUltimoHijo().accept(this);
        System.out.print("]");
    }
    public void visit(NotNodo n){
        System.out.println("[¬]");
        System.out.print("[");
        n.getPrimerHijo().accept(this);
        System.out.print("]");
    }
    public void visit(Hoja n){

    }
    public void visit(IdentifierHoja n){
	System.out.print("[Hoja Identificador] id: "+ n.getNombre());
    }
    public void visit(IntHoja n){
	System.out.print("[Hoja Entera] valor: " + n.getValor().ival);
    }
    public void visit(BoolHoja n){
	System.out.print("[Hoja Booleana] valor: " + n.getValor().bval);
    }
    public void visit(CadenaHoja n){
	System.out.print("[Hoja Cadena] valor: " + n.getValor().sval);
    }
    public void visit(Nodo n){

    }
    public void visit(NodoBinario n){

    }
    public void visit(NodoStmts n){
    }
}
