package adc;

import java.util.Hashtable;

public class TablaSimbolo{
  protected Hashtable<String, Object> h;

  public TablaSimbolo(){
    h = new Hashtable<String, Object>();
  }

  public Object lookUp(String name){
    return h.get(name);
  }

  public void insert(String name, Object info){
    h.put(name, info);
  }
}
