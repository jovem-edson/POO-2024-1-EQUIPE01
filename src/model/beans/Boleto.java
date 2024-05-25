package model.beans;

public class Boleto extends Pagamento {
    private String codigoBarras;
    private String dataVencimento;

    public Boleto(int idPagamento, String codigoBarras, String dataVencimento) {
        super(idPagamento);
        this.codigoBarras = codigoBarras;
        this.dataVencimento = dataVencimento;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public void aplicarDesconto(Carrinho carrinho) {
        double total = carrinho.getTotalCompra();
        double desconto = total * 0.1; // 10% de desconto para boleto
        carrinho.aplicarDesconto(desconto);
    }
    
    @Override
    public boolean validarInformacoes() {
        return codigoBarras != null && !codigoBarras.isEmpty() && dataVencimento != null && !dataVencimento.isEmpty();
    }
}
