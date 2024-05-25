package model.beans;

public class Midia {
		
		private int idMidia;
	    private String nome;
	    private double preco;
	    private String classIndicativa;
	    private String genero;
	    private int qtdEstoque;
	    private String capa;
	    private String tipo;

	    public Midia() {
	    	this.idMidia = 0;
	        this.nome = "";
	        this.preco = 0;
	        this.classIndicativa = "";
	        this.genero = "";
	        this.qtdEstoque = 0;
	        this.capa = "";
	        this.tipo = "";
	    }

	    public Midia(int idMidia, String nome, double preco, String classIndicativa, String genero, int qtdEstoque, String capa, String tipo) {
	    	this.idMidia = idMidia;
	        this.nome = nome;
	        this.preco = preco;
	        this.classIndicativa = classIndicativa;
	        this.genero = genero;
	        this.qtdEstoque = qtdEstoque;
	        this.capa = capa;
	        this.tipo = tipo;

	    }

		public int getIdMidia() {
	        return idMidia;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public double getPreco() {
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
	    
	    public String getCapa() {
	    	return capa;
	    }
	    
	    public String getTipo() {
	    	return tipo;
	    }

	    public void setIdMidia(int idMidia) {
	        this.idMidia = idMidia;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public void setPreco(double preco) {
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
	    
	    public void setCapa(String capa) {
	    	this.capa = capa;
	    }
	    
	    public void setTipo(String tipo) {
	    	this.tipo = tipo;
	    }

	    public double calcularPrecoFinal() {
	        return this.preco;
	    }
	}

