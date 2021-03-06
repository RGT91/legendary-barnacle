package ast.patron.visitante;
import ast.patron.compuesto.*;

public interface Visitor
{
    public void visit(AddNodo n);  //
    public void visit(AsigNodo n);
    public void visit(Compuesto n);
    public void visit(DifNodo n);  //
    public void visit(WhileNodo n);
    public void visit(IfNodo n);
    public void visit(FactorNodo n);
    public void visit(PrintNodo n);
    public void visit(IfElseNodo n);
    public void visit(MultNodo n);
    public void visit(DivNodo n);
    public void visit(PotNodo n);
    public void visit(LTNodo n);
    public void visit(GTNodo n);
    public void visit(LENodo n);
    public void visit(GENodo n);
    public void visit(EqNodo n);
    public void visit(NEqNodo n);
    public void visit(ModNodo n);
    public void visit(DivENodo n);
    public void visit(AndNodo n);
    public void visit(OrNodo n);
    public void visit(NotNodo n);
    public void visit(Hoja n);
    public void visit(IdentifierHoja n);
    public void visit(IntHoja n);
    public void visit(BoolHoja n);
    public void visit(FloatHoja n);
    public void visit(CadenaHoja n);
    public void visit(PositivoHoja n);
    public void visit(NegativoHoja n);
    public void visit(Nodo n);
    public void visit(NodoBinario n);
    public void visit(NodoStmts n);
}
