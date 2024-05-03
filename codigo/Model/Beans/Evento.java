package codigo;

public class Evento {
  private String nomeEvento, dataEvento, horaEvento, localEvento, tipoEvento;
  private int codEvento;

  public Evento(String nomeEvento, String dataEvento, String horaEvento, String localEvento, String tipoEvento,
      int codEvento) {
    this.nomeEvento = nomeEvento;
    this.dataEvento = dataEvento;
    this.horaEvento = horaEvento;
    this.localEvento = localEvento;
    this.tipoEvento = tipoEvento;
    this.codEvento = codEvento;
  }

  public String getNomeEvento() {
    return nomeEvento;
  }

  public void setNomeEvento(String nomeEvento) {
    this.nomeEvento = nomeEvento;
  }

  public String getDataEvento() {
    return dataEvento;
  }

  public void setDataEvento(String dataEvento) {
    this.dataEvento = dataEvento;
  }

  public String getHoraEvento() {
    return horaEvento;
  }

  public void setHoraEvento(String horaEvento) {
    this.horaEvento = horaEvento;
  }

  public String getLocalEvento() {
    return localEvento;
  }

  public void setLocalEvento(String localEvento) {
    this.localEvento = localEvento;
  }

  public String getTipoEvento() {
    return tipoEvento;
  }

  public void setTipoEvento(String tipoEvento) {
    this.tipoEvento = tipoEvento;
  }

  public int getCodEvento() {
    return codEvento;
  }

  public void setCodEvento(int codEvento) {
    this.codEvento = codEvento;
  }

  @Override
  public String toString() {
    return "Evento{" + "nomeEvento='" + nomeEvento + '\'' + ", dataEvento='" + dataEvento + '\'' + ", horaEvento='"
        + horaEvento + '\'' + ", localEvento='" + localEvento + '\'' + ", descricaoEvento='" + tipoEvento + '\''
        + ", codEvento='" + +'}';
  }
}