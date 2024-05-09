package model.beans;

public class Filme extends Midia{
	
		private String duracao;
	    private String[] elenco;

	    public Filme() {
	    }

	    public Filme(int id, String nome, float preco, String classIndicativa, String genero, int qtdEstoque, String duracao, String[] elenco) {
	        super(id, nome, preco, classIndicativa, genero, qtdEstoque);
	        this.duracao = duracao;
	        this.elenco = elenco;
	    }

	    public String getDuracao() {
	        return duracao;
	    }

	    public String[] getElenco() {
	        return elenco;
	    }

	    public void setDuracao(String duracao) {
	        this.duracao = duracao;
	    }

	    public void setElenco(String[] elenco) {
	        this.elenco = elenco;
	    }
	}
