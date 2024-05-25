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
    
    @Override
    public void aplicarDesconto(Carrinho carrinho) {
        double total = carrinho.getTotalCompra();
        double desconto = total * 0.05; // 5% de desconto para cartão
        carrinho.aplicarDesconto(desconto);
    }

    @Override
    public boolean validarInformacoes() {
        return nomeTitular != null && !nomeTitular.isEmpty()
            && numeroCartao != null && numeroCartao.matches("\\d{16}")
            && cvv > 99 && cvv < 1000
            && validade != null && validade.matches("(0[1-9]|1[0-2])/[0-9]{2}");
    }
}
