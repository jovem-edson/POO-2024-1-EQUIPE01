package codigo;

public class Promotor {
  private String nomePromotor, nomeEmpresa, cnpjEmpresa, contatoEmpresa;

  public Promotor(String nomePromotor, String nomeEmpresa, String cnpjEmpresa, String contatoEmpresa) {
    this.nomePromotor = nomePromotor;
    this.nomeEmpresa = nomeEmpresa;
    this.cnpjEmpresa = cnpjEmpresa;
    this.contatoEmpresa = contatoEmpresa;
  }

  public String getNomePromotor() {
    return nomePromotor;
  }

  public void setNomePromotor(String nomePromotor) {
    this.nomePromotor = nomePromotor;
  }

  public String getNomeEmpresa() {
    return nomeEmpresa;
  }

  public void setNomeEmpresa(String nomeEmpresa) {
    this.nomeEmpresa = nomeEmpresa;
  }

  public String getCnpjEmpresa() {
    return cnpjEmpresa;
  }

  public void setCnpjEmpresa(String cnpjEmpresa) {
    this.cnpjEmpresa = cnpjEmpresa;
  }

  public String getContatoEmpresa() {
    return contatoEmpresa;
  }

  public void setContatoEmpresa(String contatoEmpresa) {
    this.contatoEmpresa = contatoEmpresa;
  }

  @Override
  public String toString() {
    return "Promotor{" +
        "nomePromotor='" + nomePromotor + '\'' +
        ", nomeEmpresa='" + nomeEmpresa + '\'' +
        ", cnpjEmpresa='" + cnpjEmpresa + '\'' +
        ", contatoEmpresa='" + contatoEmpresa + '\'' +
        '}';
  }
}
