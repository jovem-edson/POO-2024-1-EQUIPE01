package model.beans;

public class Jogo extends Midia {
	
	 private int idJogo;	
	 private boolean isOnline;

	    public Jogo() {
	    }

	    public Jogo(int idMidia, String nome, double preco, String classIndicativa, String genero, int qtdEstoque, String capa, String tipo, int idJogo, boolean isOnline) {
	        super(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo);
	        this.idJogo = idJogo;
	        this.isOnline = isOnline;
	    }
	    
	    public int getIdJogo() {
	    	return idJogo;
	    }

	    public boolean getIsOnline() {
	        return isOnline;
	    }

	    public void setIsOnline(boolean isOnline) {
	        this.isOnline = isOnline;
	    }

}
