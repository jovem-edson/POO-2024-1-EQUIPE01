package model.beans;

public class Jogo extends Midia {
	
	 private boolean isOnline;

	    public Jogo() {
	    }

	    public Jogo(int id, String nome, float preco, String classIndicativa, String genero, int qtdEstoque, boolean isOnline) {
	        super(id, nome, preco, classIndicativa, genero, qtdEstoque);
	        this.isOnline = isOnline;
	    }

	    public boolean getIsOnline() {
	        return isOnline;
	    }

	    public void setIsOnline(boolean isOnline) {
	        this.isOnline = isOnline;
	    }

}
