package model.dao;

import model.beans.Filme;
import java.sql.*;
import javax.swing.JOptionPane;

public class FilmeDAO {

	PreparedStatement st;

	public void cadastrarFilme() {
		try (Connection conecta = ConectaBD.conexao()) {
			Filme filme = new Filme();
			filme.setNome(JOptionPane.showInputDialog("Digite o nome do filme: "));
			filme.setPreco(Float.parseFloat(JOptionPane.showInputDialog("Digite o preço do filme: ")));
			filme.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do filme: "));
			filme.setGenero(JOptionPane.showInputDialog("Digite o gênero do filme: "));
			filme.setQtdEstoque(
					Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do filme: ")));

			try {
				st = conecta.prepareStatement(
						"INSERT INTO filme (nome, preco, classIndicativa, genero, qtdEstoque) VALUES (?,?,?,?,?)");
				st.setString(1, filme.getNome());
				st.setFloat(2, filme.getPreco());
				st.setString(3, filme.getClassIndicativa());
				st.setString(4, filme.getGenero());
				st.setInt(5, filme.getQtdEstoque());

				// Executando o comando INSERT
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Filme já cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar filme: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void listarFilme() {
		try (Connection conecta = ConectaBD.conexao()) {
			String dadosFilmes = "";
			try {
				st = conecta.prepareStatement("SELECT * FROM filme ORDER BY idFilme");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					dadosFilmes += "ID: " + rs.getInt("idFilme") + "\n" + "Nome: " + rs.getString("nome") + "\n"
							+ "Preço: " + rs.getFloat("preco") + "\n" + "Classificação Indicativa: "
							+ rs.getString("classIndicativa") + "\n" + "Gênero: " + rs.getString("genero") + "\n"
							+ "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosFilmes);
			} catch (SQLException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, "Erro ao listar filmes: " + erro);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void consultarFilme(String nome) {
		try (Connection conecta = ConectaBD.conexao()) {
			String dadosFilmes = "";
			try {
				st = conecta.prepareStatement("SELECT * FROM filme WHERE nome LIKE ? ORDER BY idFilme");
				st.setString(1, "%" + nome + "%");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					dadosFilmes += "ID: " + rs.getInt("idFilme") + "\n" + "Nome: " + rs.getString("nome") + "\n"
							+ "Preço: " + rs.getFloat("preco") + "\n" + "Classificação Indicativa: "
							+ rs.getString("classIndicativa") + "\n" + "Gênero: " + rs.getString("genero") + "\n"
							+ "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosFilmes);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar filme: " + e.getMessage());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void alterarFilme(int idFilme) {
		try (Connection conecta = ConectaBD.conexao()) {
			Filme filme = new Filme();
			filme.setNome(JOptionPane.showInputDialog("Digite o nome do filme: "));
			filme.setPreco(Float.parseFloat(JOptionPane.showInputDialog("Digite o preço do filme: ")));
			filme.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do filme: "));
			filme.setGenero(JOptionPane.showInputDialog("Digite o gênero do filme: "));
			filme.setQtdEstoque(
					Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do filme: ")));
			try {
				st = conecta.prepareStatement(
						"UPDATE filme SET nome = ?, preco = ?, classIndicativa = ?, genero = ?, qtdEstoque = ? WHERE idFilme = ?");
				st.setString(1, filme.getNome());
				st.setFloat(2, filme.getPreco());
				st.setString(3, filme.getClassIndicativa());
				st.setString(4, filme.getGenero());
				st.setInt(5, filme.getQtdEstoque());
				st.setInt(6, idFilme);
				// Executando o comando UPDATE
				st.executeUpdate();
				JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Filme já cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar filme: " + erro);
				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

	public void deletarFilme(int idFilme) {
		try (Connection conecta = ConectaBD.conexao()) {
			try {
				st = conecta.prepareStatement("DELETE FROM filme WHERE idFilme=?");
				st.setInt(1, idFilme);

				// Executa o comando DELETE
				int resultado = st.executeUpdate();

				// Verifica se o filme foi removido
				if (resultado == 0)
					JOptionPane.showMessageDialog(null, "Filme não encontrado");
				else
					JOptionPane.showMessageDialog(null, "O filme de registro " + idFilme + " foi removido com sucesso");

			} catch (SQLException e) {
				String erro = e.getMessage();
				JOptionPane.showMessageDialog(null, "Erro ao deletar filme: " + erro);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
		}
	}

}
