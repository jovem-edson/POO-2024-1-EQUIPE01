package model.beans;

public class Midia {
		
		private int idMidia;
	    private String nome;
	    private float preco;
	    private String classIndicativa;
	    private String genero;
	    private int qtdEstoque;

	    public Midia() {
	    }

	    public Midia(int idMidia, String nome, float preco, String classIndicativa, String genero, int qtdEstoque) {
	        this.idMidia = idMidia;
	        this.nome = nome;
	        this.preco = preco;
	        this.classIndicativa = classIndicativa;
	        this.genero = genero;
	        this.qtdEstoque = qtdEstoque;
	    }

	    public int getIdMidia() {
	        return idMidia;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public float getPreco() {
	        return preco;
	    }

	    public String getClassIndicativa() {
	        return classIndicativa;
	    }

	    public String getGenero() {
	        return genero;
	    }

	    public int getQtdEstoque() {
	        return qtdEstoque;
	    }

	    public void setIdMidia(int idMidia) {
	        this.idMidia = idMidia;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public void setPreco(float preco) {
	        this.preco = preco;
	    }

	    public void setClassIndicativa(String classIndicativa) {
	        this.classIndicativa = classIndicativa;
	    }

	    public void setGenero(String genero) {
	        this.genero = genero;
	    }

	    public void setQtdEstoque(int qtdEstoque) {
	        this.qtdEstoque = qtdEstoque;
	    }

	    public float calcularPrecoFinal() {
	        return this.preco;
	    }
	}

