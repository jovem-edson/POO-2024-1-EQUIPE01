package model.beans;

public class Artista {
	private int idArtista;
    private String nome;

    public Artista() {
    }

    public Artista(int idArtista, String nome) {
        this.idArtista = idArtista;
        this.nome = nome;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public String getNome() {
        return nome;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
