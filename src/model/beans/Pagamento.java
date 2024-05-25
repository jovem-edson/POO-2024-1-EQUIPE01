package model.beans;

public abstract class Pagamento {
	private int idPagamento;
	private double valor;

    public Pagamento() {
    }

    public Pagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }
    
    
    public Pagamento(double valor) {
        this.valor = valor;
    }
    
    public double getValor() {
        return valor;
    }
    
    public double calcularValorComDesconto() {
    	return 0.0;
    };
    
    
    public void realizarPagamento() {

    }
    
    public abstract boolean validarInformacoes();

    
    public abstract void aplicarDesconto(Carrinho carrinho);



    
   
}
