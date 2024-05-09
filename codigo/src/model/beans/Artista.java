package model.beans;

public class Artista {
	private int idArtista;
    private String nomeArtista;

    public Artista() {
    }

    public Artista(int idArtista, String nomeArtista) {
        this.idArtista = idArtista;
        this.nomeArtista = nomeArtista;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

}
