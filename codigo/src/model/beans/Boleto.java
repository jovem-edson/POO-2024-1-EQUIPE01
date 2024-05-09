package model.beans;

public class Boleto extends Pagamento{
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
}
