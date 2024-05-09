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

}
