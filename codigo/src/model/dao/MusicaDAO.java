package model.dao;

import model.beans.Musica;
import java.sql.*;
import javax.swing.JOptionPane;

public class MusicaDAO {

	PreparedStatement st;

	public void cadastrarMusica() {
		try (Connection conecta = ConectaBD.conexao()) {
			Musica musica = new Musica();
			musica.setTitulo(JOptionPane.showInputDialog("Digite o título da música: "));
			musica.setDuracao(JOptionPane.showInputDialog("Digite a duração da música: "));
			// HOMENS FALTA O ARTISTA HOMENS, SETTEM O ARTISTA HOMENS

			try {
				st = conecta.prepareStatement("INSERT INTO musica (titulo, duracao) VALUES (?,?)");
				st.setString(1, musica.getTitulo());
				st.setString(2, musica.getDuracao());

				// Executando o comando INSERT
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Música cadastrada com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Música já cadastrada!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar música: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void listarMusica() {
		try (Connection conecta = ConectaBD.conexao()) {
			String dadosMusicas = "";
			try {
				st = conecta.prepareStatement("SELECT * FROM musica ORDER BY idMusica");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					dadosMusicas += "ID: " + rs.getInt("idMusica") + "\n" + "Título: " + rs.getString("titulo") + "\n"
							+ "Duração: " + rs.getString("duracao") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosMusicas);
			} catch (SQLException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, "Erro ao listar músicas: " + erro);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void consultarMusica(String titulo) {
		try (Connection conecta = ConectaBD.conexao()) {
			String dadosMusicas = "";
			try {
				st = conecta.prepareStatement("SELECT * FROM musica WHERE titulo LIKE ? ORDER BY idMusica");
				st.setString(1, "%" + titulo + "%");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					dadosMusicas += "ID: " + rs.getInt("idMusica") + "\n" + "Título: " + rs.getString("titulo") + "\n"
							+ "Duração: " + rs.getString("duracao") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosMusicas);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar música: " + e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void alterarMusica(int idMusica) {
		try (Connection conecta = ConectaBD.conexao()) {
			Musica musica = new Musica();
			musica.setTitulo(JOptionPane.showInputDialog("Digite o título da música: "));
			musica.setDuracao(JOptionPane.showInputDialog("Digite a duração da música: "));
			//HOMENS FALTA O ARTISTA HOMENS, SETTEM O ARTISTA HOMENS			// Exemplo: musica.setArtista(selecionarArtista());

			try {
				st = conecta.prepareStatement("UPDATE musica SET titulo = ?, duracao = ? WHERE idMusica = ?");
				st.setString(1, musica.getTitulo());
				st.setString(2, musica.getDuracao());
				st.setInt(3, idMusica);
				// Executando o comando UPDATE
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Música atualizada com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Música já cadastrada!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar música: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void deletarMusica(int idMusica) {
		try (Connection conecta = ConectaBD.conexao()) {
			try {
				st = conecta.prepareStatement("DELETE FROM musica WHERE idMusica=?");
				st.setInt(1, idMusica);

				// Executa o comando DELETE
				int resultado = st.executeUpdate();

				// Verifica se a música foi removida
				if (resultado == 0)
					JOptionPane.showMessageDialog(null, "Música não encontrada");
				else
					JOptionPane.showMessageDialog(null,
							"A música de registro " + idMusica + " foi removida com sucesso");

			} catch (SQLException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, "Erro ao deletar música: " + erro);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

}
