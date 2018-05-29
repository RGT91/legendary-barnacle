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

  protected int[][] compTabla = new int[][]{
    { 0, 0, 0, 0, 0 },
    { 0, 3, 3, 3, 3 },
    { 0, 3, 3, 3, 3 },
    { 0, 3, 3, 3, 3 },
    { 0, 3, 3, 3, 3 }
  };

  public TablaSimbolo h = new TablaSimbolo();

  public void visit(AddNodo n){
    n.getPrimerHijo().accept(this);
    n.getUltimoHijo().accept(this);
    Nodo fChild =  n.getPrimerHijo();
    Nodo lChild = n.getUltimoHijo();
    int type=addTabla[fChild.getType()][lChild.getType()];
    if(type!=0){
      n.setTipo(type);
    }else{
      System.out.println("ERROR: de tipos");
      System.exit(1);
    }
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
      int type=difTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(WhileNodo n){
        n.getPrimerHijo().accept(this);
        if(n.getPrimerHijo().getType()!= 3){
          System.out.println("condición no es booleana");
          System.exit(1);
        }
        n.getUltimoHijo().accept(this);
    }
    public void visit(IfNodo n){
        n.getPrimerHijo().accept(this);
        if(n.getPrimerHijo().getType()!= 3){
          System.out.println("condición no es booleana");
          System.exit(1);
        }
        n.getUltimoHijo().accept(this);
    }
    public void visit(IfElseNodo n){
      n.getPrimerHijo().accept(this);
      if(n.getPrimerHijo().getType()!= 3){
        System.out.println("condición no es booleana");
        System.exit(1);
      }
      n.getIntermedio().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(PrintNodo n){
        n.getPrimerHijo().accept(this);
    }
    public void visit(MultNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=mulTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(DivNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=difTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(PotNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=difTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(LTNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=compTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(GTNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=compTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(LENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=compTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(GENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=compTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(EqNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=compTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(FactorNodo n){
        n.getPrimerHijo().accept(this);
        n.getUltimoHijo().accept(this);
        n.setTipo(n.getUltimoHijo().getType());
    }
    public void visit(NEqNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=compTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(ModNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=difTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }

    public void visit(DivENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=difTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(AndNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=orTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(OrNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
      Nodo fChild =  n.getPrimerHijo();
      Nodo lChild = n.getUltimoHijo();
      int type=orTabla[fChild.getType()][lChild.getType()];
      if(type!=0){
        n.setTipo(type);
      }else{
        System.out.println("ERROR: de tipos");
        System.exit(1);
      }
    }
    public void visit(NotNodo n){
        n.getPrimerHijo().accept(this);
        if(n.getPrimerHijo().getType()!=0){
          n.setTipo(3);
        }else{
          System.out.println("ERROR: Tipo no valido para negación");
          System.exit(1);
        }
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
