package codigo;

public class Ingresso {
  private int codIngresso;

  public Ingresso(){}
  
  public Ingresso(int codIngresso) {
    this.codIngresso = codIngresso;
  }

  public int getCodIngresso() {
    return codIngresso;
  }

  public void setCodIngresso(int codIngresso) {
    this.codIngresso = codIngresso;
  }

  @Override
  public String toString() {
    return "Ingresso{" +
        "codIngresso=" + codIngresso +
        '}';
  }

}