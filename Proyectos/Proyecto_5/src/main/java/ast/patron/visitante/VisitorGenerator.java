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

        String opcode =  tipo==2 ? "asig.s" : "asig";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
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

      String opcode =  tipo==2 ? "mul.s" : "mul";

      System.out.println(opcode + " " + objetivo + ", " +
                          siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(DivNodo n){
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

      String opcode =  tipo==2 ? "div.s" : "div";

      System.out.println(opcode + " " + objetivo + ", " +
                          siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(PotNodo n){
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

        String opcode =  tipo==2 ? "pot.s" : "pot";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(LTNodo n){
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

        String opcode =  tipo==2 ? "less.s" : "less";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(GTNodo n){
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

        String opcode =  tipo==2 ? "great.s" : "great";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(LENodo n){
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

        String opcode =  tipo==2 ? "lessE.s" : "lessE";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(GENodo n){
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

        String opcode =  tipo==2 ? "greatE.s" : "greatE";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(EqNodo n){
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

        String opcode =  tipo==2 ? "eq.s" : "eq";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(FactorNodo n){
      n.getPrimerHijo().accept(this);
      n.getUltimoHijo().accept(this);
    }
    public void visit(NEqNodo n){
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

        String opcode =  tipo==2 ? "notEq.s" : "notEq";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(ModNodo n){
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

        String opcode =  tipo==2 ? "mod.s" : "mod";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(DivENodo n){
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

        String opcode =  tipo==2 ? "divE.s" : "divE";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(AndNodo n){
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

        String opcode =  tipo==2 ? "and.s" : "and";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(OrNodo n){
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

        String opcode =  tipo==2 ? "or.s" : "or";

        System.out.println(opcode + " " + objetivo + ", " +
                            siguientes[0] + ", " + siguientes[1]);
    }
    public void visit(NotNodo n){
      n.getPrimerHijo().accept(this);
      Nodo hi = n.getPrimerHijo();

      // Tipo de registro objetivo
      int tipo = n.getType();
      boolean entero =  tipo==2 ? false : true;

      int objetivo = reg.getObjetivo(entero);
      String[] siguientes = reg.getNsiguientes(1,entero);

      // Genero el código del subárbol izquiero
      reg.setObjetivo(siguientes[0],entero);
      hi.accept(this);

      String opcode =  tipo==2 ? "not.s" : "not";

      System.out.println(opcode + " " + objetivo + ", " +
                          siguientes[0]);
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
