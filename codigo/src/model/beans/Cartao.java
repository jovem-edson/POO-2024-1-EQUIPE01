package model.beans;

public class Cartao extends Pagamento {
	private String nomeTitular;
    private String numeroCartao;
    private int cvv;
    private String validade;

    public Cartao(int idPagamento, String nomeTitular, String numeroCartao, int cvv, String validade) {
        super(idPagamento);
        this.nomeTitular = nomeTitular;
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.validade = validade;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public int getCVV() {
        return cvv;
    }

    public String getValidade() {
        return validade;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setCVV(int cvv) {
        this.cvv = cvv;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
