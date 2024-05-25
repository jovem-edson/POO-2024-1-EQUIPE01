package model.beans;

import java.util.ArrayList;

public class Album extends Midia {
    private int anoLancamento;
    private ArrayList<String> artistas;
    private ArrayList<String> musicas;

    public Album() {
    	this.artistas = new ArrayList<>();
    	this.musicas = new ArrayList<>();
    }

    public Album(int idMidia, String nome, double preco, String classIndicativa, String genero, int qtdEstoque, String capa, String tipo, int idAlbum, int anoLancamento, ArrayList<String> artista, ArrayList<String> musica) {
        super(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo);
        this.anoLancamento = anoLancamento;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public ArrayList<String> getArtistas() {
        return artistas;
    }

    public ArrayList<String> getMusicas() {
        return musicas;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setArtistas(ArrayList<String> artistas) {
        this.artistas = artistas;
    }

    public void setMusicas(ArrayList<String> musicas) {
        this.musicas = musicas;
    }
    
    public void adicionarMusica(String musica) {
    	musicas.add(musica);
    }
    
    public void removerMusica(String musica) {
    	musicas.remove(musica);
    }
    
    public void adicionarArtista(String artista) {
    	artistas.add(artista);
    }
    
    public void removerArtista(String artista) {
    	artistas.remove(artista);
    }

}
