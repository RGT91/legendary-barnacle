package adc;

import java.util.Hashtable;
import ast.patron.compuesto.IdentifierHoja;

public class TablaSimbolo{
  protected Hashtable<String, IdentifierHoja> h;

  public TablaSimbolo(){
    h = new Hashtable<String, IdentifierHoja>();
  }

  public IdentifierHoja lookUp(String name){
    return h.get(name);
  }

  public void insert(String name, IdentifierHoja info){
    h.put(name, info);
  }
}
