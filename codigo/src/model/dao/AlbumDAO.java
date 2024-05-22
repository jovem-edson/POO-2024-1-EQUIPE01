package model.dao;

import model.beans.Album;
import model.beans.Artista;
import model.beans.Musica;

import java.sql.*;
import java.util.Iterator;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class AlbumDAO {

	PreparedStatement st;

	public void cadastrarAlbum() {
		try (Connection conecta = ConectaBD.conexao()) {
			Album album = new Album();
			Artista artista = new Artista();
			ArrayList<Musica> musicas = new ArrayList<Musica>(); 

			album.setNome(JOptionPane.showInputDialog("Digite o nome do álbum: "));
			album.setPreco(Float.parseFloat(JOptionPane.showInputDialog("Digite o preço do álbum: ")));
			album.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do álbum: "));
			album.setGenero(JOptionPane.showInputDialog("Digite o gênero do álbum: "));
			album.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do álbum: ")));
			album.setCapa(JOptionPane.showInputDialog("Digite o caminho da capa do álbum: "));
			album.setAnoLancamento(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de lançamento do álbum: ")));
			
			artista.setIdArtista(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do artista do álbum: ")));
			artista.setNome(JOptionPane.showInputDialog("Digite o ID da música do álbum: "));
			
			int qtdMusicas = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de musicas no álbum: "));

			for (int i = 1; i <= qtdMusicas; i++) {
				Musica musica = new Musica();
				musica.setTitulo(JOptionPane.showInputDialog("Digite o título da música " + i + " do album: "));
				musica.setDuracao(JOptionPane.showInputDialog("Digite a duração da música: " + i + " do album: "));
				musica.setArtista(artista);
				musicas.add(musica);
			}
			
			 

			try {
				st = conecta.prepareStatement("INSERT INTO album (nome, preco, classIndicativa, genero, qtdEstoque, capa, anoLancamento, fk_idArtista, fk_idMusica) VALUES (?,?,?,?,?,?,?,?,?)");
				st.setString(1, album.getNome());
				st.setDouble(2, album.getPreco());
				st.setString(3, album.getClassIndicativa());
				st.setString(4, album.getGenero());
				st.setInt(5, album.getQtdEstoque());
				st.setString(6, album.getCapa());
				st.setInt(7, album.getAnoLancamento());
				st.setInt(8, artista.getIdArtista());
				st.setInt(9, musica.getIdMusica());

				// Executando o comando INSERT
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Álbum cadastrado com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Álbum já cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar álbum: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}

	public void listarAlbum() {
		try (Connection conecta = ConectaBD.conexao()) {
			String dadosAlbums = "";
			try {
				st = conecta.prepareStatement("SELECT * FROM album ORDER BY idAlbum");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					dadosAlbums += "ID: " + rs.getInt("idAlbum") + "\n" + "Nome: " + rs.getString("nome") + "\n"
							+ "Preço: " + rs.getDouble("preco") + "\n" + "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n"
							+ "Gênero: " + rs.getString("genero") + "\n" + "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n"
							+ "Capa: " + rs.getString("capa") + "\n" + "Ano de Lançamento: " + rs.getInt("anoLancamento") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosAlbums);
			} catch (SQLException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, "Erro ao listar álbuns: " + erro);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}

	public void consultarAlbum(String nome) {
		try (Connection conecta = ConectaBD.conexao()) {
			String dadosAlbums = "";
			try {
				st = conecta.prepareStatement("SELECT * FROM album WHERE nome LIKE ? ORDER BY idAlbum");
				st.setString(1, "%" + nome + "%");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					dadosAlbums += "ID: " + rs.getInt("idAlbum") + "\n" + "Nome: " + rs.getString("nome") + "\n"
							+ "Preço: " + rs.getDouble("preco") + "\n" + "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n"
							+ "Gênero: " + rs.getString("genero") + "\n" + "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n"
							+ "Capa: " + rs.getString("capa") + "\n" + "Ano de Lançamento: " + rs.getInt("anoLancamento") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosAlbums);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar álbum: " + e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}

	public void alterarAlbum(int idAlbum) {
		try (Connection conecta = ConectaBD.conexao()) {
			Album album = new Album();
			album.setNome(JOptionPane.showInputDialog("Digite o nome do álbum: "));
			album.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do álbum: ")));
			album.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do álbum: "));
			album.setGenero(JOptionPane.showInputDialog("Digite o gênero do álbum: "));
			album.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do álbum: ")));
			album.setCapa(JOptionPane.showInputDialog("Digite o caminho da capa do álbum: "));
			album.setAnoLancamento(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de lançamento do álbum: ")));
			album.setFk_idArtista(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do artista do álbum: ")));
			album.setFk_idMusica(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da música do álbum: ")));

			try {
				st = conecta.prepareStatement("UPDATE album SET nome = ?, preco = ?, classIndicativa = ?, genero = ?, qtdEstoque = ?, capa = ?, anoLancamento = ?, fk_idArtista = ?, fk_idMusica = ? WHERE idAlbum = ?");
				st.setString(1, album.getNome());
				st.setDouble(2, album.getPreco());
				st.setString(3, album.getClassIndicativa());
				st.setString(4, album.getGenero());
				st.setInt(5, album.getQtdEstoque());
				st.setString(6, album.getCapa());
				st.setInt(7, album.getAnoLancamento());
				st.setInt(8, album.getFk_idArtista());
				st.setInt(9, album.getFk_idMusica());
				st.setInt(10, idAlbum);
				// Executando o comando UPDATE
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Álbum atualizado com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Álbum já cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar álbum: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}

	public void deletarAlbum(int idAlbum) {
		try (Connection conecta = ConectaBD.conexao()) {
			try {
				st = conecta.prepareStatement("DELETE FROM album WHERE idAlbum=?");
				st.setInt(1, idAlbum);

				// Executa o comando DELETE
				int resultado = st.executeUpdate();

				// Verifica se o álbum foi removido
				if (resultado == 0)
					JOptionPane.showMessageDialog(null, "Álbum não encontrado");
				else
					JOptionPane.showMessageDialog(null,
							"O álbum de registro " + idAlbum + " foi removido com sucesso");

			} catch (SQLException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, "Erro ao deletar álbum: " + erro);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}
}
