package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.LinkedList;
import java.util.Iterator;
import adc.TablaSimbolo;

public class VisitorType implements Visitor
{
  protected int[][] addTabla = new int[][]{
    { 0, 0, 0, 0, 0 },
    { 0, 1, 2, 3, 4 },
    { 0, 2, 2, 3, 4 },
    { 0, 3, 3, 3, 3 },
    { 0, 4, 4, 3, 4 }
  };
  protected int[][] difTabla = new int[][]{
    { 0, 0, 0, 0, 0 },
    { 0, 1, 2, 1, 0 },
    { 0, 2, 2, 2, 0 },
    { 0, 1, 2, 1, 0 },
    { 0, 0, 0, 0, 0 }
  };
  protected int[][] mulTabla = new int[][]{
    { 0, 0, 0, 0, 0 },
    { 0, 1, 2, 1, 4 },
    { 0, 2, 2, 2, 0 },
    { 0, 1, 2, 1, 4 },
    { 0, 4, 0, 4, 0 }
  };

  protected int[][] orTabla = new int[][]{
    { 0, 0, 0, 0, 0 },
    { 0, 1, 0, 1, 0 },
    { 0, 0, 0, 0, 0 },
    { 0, 1, 0, 3, 0 },
    { 0, 0, 0, 0, 0 }
  };

  public TablaSimbolo h = new TablaSimbolo();

  public void visit(AddNodo n){
    n.getPrimerHijo().accept(this);
    n.getUltimoHijo().accept(this);
    Nodo fChild =  n.getPrimerHijo();
    Nodo lChild = n.getUltimoHijo();
    n.setTipo(addTabla[fChild.getType()][lChild.getType()]);
  }
    public void visit(AsigNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      if(h.lookUp(n.getPrimerHijo().getNombre())==null){
        n.getPrimerHijo().setTipo(n.getUltimoHijo().getType());
        h.insert(n.getPrimerHijo().getNombre(), (IdentifierHoja)n.getPrimerHijo());
        n.setTipo(n.getPrimerHijo().getType());
      }else{
        n.getPrimerHijo().setTipo(
          h.lookUp(n.getPrimerHijo().getNombre()).getType()
        );
        if(n.getPrimerHijo().getType() != n.getUltimoHijo().getType()){
          System.exit(1);
        }
        n.setTipo(n.getPrimerHijo().getType());
      }
    }
    public void visit(Compuesto n){
        for (Iterator i = n.getHijos().iterator(); i.hasNext(); ) {
            Nodo hijo = (Nodo) i.next();
            hijo.accept(this);
        }

    }
    public void visit(DifNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(difTabla[fChild.getType()][lChild.getType()]);
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
      n.getIntermedio().accept(this);
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
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(mulTabla[fChild.getType()][lChild.getType()]);
    }
    public void visit(DivNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(difTabla[fChild.getType()][lChild.getType()]);
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
    public void visit(FactorNodo n){
        System.out.println("[");
        n.getPrimerHijo().accept(this);
        System.out.print("[");
        n.getUltimoHijo().accept(this);
        System.out.print("]]");
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
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(difTabla[fChild.getType()][lChild.getType()]);
    }

    public void visit(DivENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(difTabla[fChild.getType()][lChild.getType()]);
    }
    public void visit(AndNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(orTabla[fChild.getType()][lChild.getType()]);
    }
    public void visit(OrNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      n.setTipo(orTabla[fChild.getType()][lChild.getType()]);
    }
    public void visit(NotNodo n){
        n.getPrimerHijo().accept(this);
        n.setTipo(3);
    }
    public void visit(Hoja n){

    }
    public void visit(IdentifierHoja n){
      if(h.lookUp(n.getNombre())!=null){
        n.setTipo(h.lookUp(n.getNombre()).getType());
      }
    }
    public void visit(IntHoja n){
    }
    public void visit(BoolHoja n){
    }
    public void visit(CadenaHoja n){
    }
    public void visit(FloatHoja n){
    }
    public void visit(PositivoHoja n){
    }
    public void visit(NegativoHoja n){
    }
    public void visit(Nodo n){

    }
    public void visit(NodoBinario n){

    }
    public void visit(NodoStmts n){
    }
}
