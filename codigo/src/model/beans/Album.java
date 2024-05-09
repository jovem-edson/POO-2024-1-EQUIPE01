package model.beans;

public class Album extends Midia {
	private String capa;
    private int anoLancamento;
    private Artista artista;
    private Musica musica;

    public Album() {
    }

    public Album(int id, String nome, double preco, String classIndicativa, String genero, int qtdEstoque, String capa, int anoLancamento, Artista artista, Musica musica) {
        super(id, nome, preco, classIndicativa, genero, qtdEstoque);
        this.capa = capa;
        this.anoLancamento = anoLancamento;
        this.artista = artista;
        this.musica = musica;
    }

    public String getCapa() {
        return capa;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public Artista getArtista() {
        return artista;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

}
