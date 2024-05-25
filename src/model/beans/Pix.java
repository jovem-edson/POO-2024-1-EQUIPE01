package model.beans;

public class Pix extends Pagamento {
    private String chavePix;

    public Pix(int idPagamento, String chavePix) {
        super(idPagamento);
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void aplicarDesconto(Carrinho carrinho) {
        double total = carrinho.getTotalCompra();
        double desconto = total * 0.15; // 15% de desconto para Pix
        carrinho.aplicarDesconto(desconto);
    }
    
    @Override
    public boolean validarInformacoes() {
        return chavePix != null && !chavePix.isEmpty();
    }
}
