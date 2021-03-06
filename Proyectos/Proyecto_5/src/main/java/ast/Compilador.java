//import Parser;
package ast;
import java.io.*;
import ast.patron.compuesto.*;
import ast.patron.visitante.*;


public class Compilador{

    Parser parser;
    Nodo raízAST;
    Visitor v_print;
    Visitor vType;
    Visitor vGen;

    Compilador(Reader fuente){
        parser = new Parser(fuente);
        v_print = new VisitorPrint();
        vType = new VisitorType();
        vGen = new VisitorGenerator();
    }

    public void ConstruyeAST(boolean debug){
        parser.yydebug = debug;
        parser.yyparse(); // análisis léxico, sintáctio y constucción del AST
        raízAST = parser.raíz;
    }

    public void imprimeAST(){
        parser.raíz.accept(v_print);
    }

    public void typeAST(){
        parser.raíz.accept(vType);
    }

    public void genCode(){
        parser.raíz.accept(vGen);
    }

    public static void main(String[] args){
        if(args.length>0 && !args[0].equals("")){
            String archivo = args[0];
            System.out.println(archivo);
            try{
                Reader a = new FileReader(archivo);
                Compilador c  = new Compilador(a);
                c.ConstruyeAST(true);
                c.typeAST();
                System.out.println("Type AST");
                c.imprimeAST();
                System.out.println("Generar codigo");
                c.genCode();
                System.out.println("Success");
            }catch(FileNotFoundException e){
                System.err.println("El archivo " + archivo +" no fue encontrado. ");
            }catch(ArrayIndexOutOfBoundsException e){
                System.err.println("Uso: java Compilador [archivo.p]: ");
            }
        }
    }
}
