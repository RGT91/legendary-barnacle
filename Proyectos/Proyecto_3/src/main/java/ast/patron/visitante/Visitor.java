package ast.patron.visitante;
import ast.patron.compuesto.*;

public interface Visitor
{
    public void visit(AddNodo n);  //
    public void visit(AsigNodo n);
    public void visit(Compuesto n);
    public void visit(DifNodo n);  //
    public void visit(CompNodo n);
    public void visit(WhileNodo n);
    public void visit(IfNodo n);
    public void visit(PrintNodo n);
    public void visit(IfElseNodo n);
    public void visit(Hoja n);
    public void visit(IdentifierHoja n);
    public void visit(IntHoja n);
    public void visit(BoolHoja n);
    public void visit(Nodo n);
    public void visit(NodoBinario n);
    public void visit(NodoStmts n);
}
