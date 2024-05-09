package model.beans;

public class Musica {
	private int idMusica;
    private String titulo;
    private String duracao;
    private Artista artista;
    private String letra;

    public Musica() {
    }

    public Musica(int idMusica, String titulo, String duracao, Artista artista, String letra) {
        this.idMusica = idMusica;
        this.titulo = titulo;
        this.duracao = duracao;
        this.artista = artista;
        this.letra = letra;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDuracao() {
        return duracao;
    }

    public Artista getArtista() {
        return artista;
    }

    public String getLetra() {
        return letra;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}