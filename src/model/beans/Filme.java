package model.beans;

import java.util.ArrayList;

public class Filme extends Midia{
	
		private int idFilme;
		private String diretor;
		private String duracao;
	    private ArrayList<String> elenco;

	    public Filme() {
	    	this.elenco = new ArrayList<>();
	    }

	    public Filme(int idMidia, String nome, double preco, String classIndicativa, String genero, int qtdEstoque, String capa, String tipo, int idFilme, String diretor, String duracao, ArrayList<String> elenco) {
	        super(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo);
	        this.idFilme = idFilme;
	        this.diretor = diretor;
	        this.duracao = duracao;
	        this.elenco = elenco;
	    }

		public int getIdFilme() {
	    	return idFilme;
	    }
		
		public String getDiretor() {
			return diretor;
		}

	    public String getDuracao() {
	        return duracao;
	    }

	    public ArrayList<String> getElenco() {
	        return elenco;
	    }
	    
	    public void setDiretor(String diretor) {
	    	this.diretor = diretor;
	    }

	    public void setDuracao(String duracao) {
	        this.duracao = duracao;
	    }
	    
	    public void setElenco(ArrayList<String> elenco) {
	        this.elenco = elenco;
	    }

	    public void adicionarAtor(String ator) {
	    	elenco.add(ator);
	    }
	    
	    public void removerAtor(String ator) {
	    	elenco.remove(ator);
	    }
	}
