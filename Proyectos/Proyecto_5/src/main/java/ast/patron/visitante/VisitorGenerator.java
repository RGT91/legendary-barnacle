package ast.patron.visitante;
import ast.patron.compuesto.*;
import java.util.Iterator;
import gen.*;


public class VisitorGenerator implements Visitor
{
    Registro reg;

    public VisitorGenerator(){
      this.reg = new Registro();
    }

    public void visit(AddNodo n){
      Nodo hi = n.getPrimerHijo();
      Nodo hd = n.getUltimoHijo();

      // Tipo de registro objetivo
      int tipo = n.getType();
      boolean entero =  tipo==2 ? false : true;

      int objetivo = reg.getObjetivo(entero);
      String[] siguientes = reg.getNsiguientes(2,entero);

      // Genero el código del subárbol izquiero
      reg.setObjetivo(siguientes[0],entero);
      hi.accept(this);

      // Genero el código del subárbol derecho
      reg.setObjetivo(siguientes[1], entero);
      hd.accept(this);

      String opcode =  tipo==2 ? "add.s" : "add";

      System.out.println(opcode + " " + objetivo + ", " +
                          siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(AsigNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(Compuesto n){
      for (Iterator i = n.getHijos().iterator(); i.hasNext(); ) {
          Nodo hijo = (Nodo) i.next();
          hijo.accept(this);
      }
    }
    public void visit(DifNodo n){
        Nodo hi = n.getPrimerHijo();
        Nodo hd = n.getUltimoHijo();

        // Tipo de registro objetivo
        int tipo = n.getType();
        boolean entero =  tipo==2 ? false : true;

        int objetivo = reg.getObjetivo(entero);
        String[] siguientes = reg.getNsiguientes(2,entero);

        // Genero el código del subárbol izquiero
        reg.setObjetivo(siguientes[0],entero);
        hi.accept(this);

        // Genero el código del subárbol derecho
        reg.setObjetivo(siguientes[1], entero);
        hd.accept(this);

        String opcode =  tipo==2 ? "sub.s" : "sub";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(WhileNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(IfNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(IfElseNodo n){
      n.getPrimerHijo().accept(this);
      n.getIntermedio().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(PrintNodo n){
      n.getPrimerHijo().accept(this);
    }
    public void visit(MultNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(DivNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(PotNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(LTNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(GTNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(LENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(GENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(EqNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(FactorNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(NEqNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(ModNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(DivENodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(AndNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(OrNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(NotNodo n){
      n.getPrimerHijo().accept(this);
    }
    public void visit(Hoja n){}
    public void visit(IdentifierHoja n){}
    public void visit(IntHoja n){}
    public void visit(BoolHoja n){}
    public void visit(FloatHoja n){}
    public void visit(CadenaHoja n){}
    public void visit(PositivoHoja n){}
    public void visit(NegativoHoja n){}
    public void visit(Nodo n){}
    public void visit(NodoBinario n){}
    public void visit(NodoStmts n){}
}
