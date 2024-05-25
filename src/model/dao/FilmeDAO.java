package model.dao;

import model.beans.Filme;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.JPanel;

public class FilmeDAO implements ListagemDAO<Filme>{
	
	PreparedStatement st;
	ResultSet rs;

	public void cadastrarFilme() {
		try (Connection conecta = ConectaBD.conexao()) {
			Filme filme = new Filme();
			filme.setNome(JOptionPane.showInputDialog("Digite o nome do filme: "));
			filme.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do filme: ")));
			filme.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do filme: "));
			filme.setGenero(JOptionPane.showInputDialog("Digite o gênero do filme: "));
			filme.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do filme: ")));
			filme.setCapa(JOptionPane.showInputDialog("Informe a URL da capa do filme: "));
			
			filme.setDiretor(JOptionPane.showInputDialog("Informe o nome do diretor do filme"));
			filme.setDuracao(JOptionPane.showInputDialog("Informe a duração do filme: "));
			
			int qtdElenco = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de atores no elenco: "));
			for(int i = 0; i < qtdElenco; i++) {
				filme.adicionarAtor(JOptionPane.showInputDialog("Informe o nome do ator"));
			}
			
			String elencoString = String.join(",", filme.getElenco());

			try {
				
				conecta.setAutoCommit(false); //desativando commit automático
				
				
				String sqlMidia = "INSERT INTO midia (nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo) VALUES (?,?,?,?,?,?,?)";
				st = conecta.prepareStatement(sqlMidia, PreparedStatement.RETURN_GENERATED_KEYS);
				st.setString(1, filme.getNome());
				st.setDouble(2, filme.getPreco());
				st.setString(3, filme.getClassIndicativa());
				st.setString(4, filme.getGenero());
				st.setInt(5, filme.getQtdEstoque());
				st.setString(6, filme.getCapa());
				st.setString(7, "filme");
				st.executeUpdate();
				
				//obtendo ID gerado
				rs = st.getGeneratedKeys();
				int lastId = 0;
				if (rs.next()) {
					lastId = rs.getInt(1);
				}
				
				rs.close();
				st.close();
				
				//Inserindo os dados na tabela filme a partir do ID obtido ali em cima
				
				String sqlFilme = "INSERT INTO filme (idFilme, diretor, duracao, elenco) VALUES (?,?,?,?)";
				st = conecta.prepareStatement(sqlFilme);
				st.setInt(1, lastId);
				st.setString(2, filme.getDiretor());
				st.setString(3, filme.getDuracao());
				st.setString(4, elencoString);
				
				// Executando o comando INSERT
				st.executeUpdate();
				conecta.commit();
				
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
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}
	
	@Override
	public void listar(JPanel mainPanel) {
        try (Connection conecta = ConectaBD.conexao()) {
            String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, f.diretor, f.duracao, f.elenco, m.tipo "
                       + "FROM midia m "
                       + "INNER JOIN filme f ON m.idMidia = f.idFilme";
            
            try (PreparedStatement st = conecta.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                
                while (rs.next()) {
                    JPanel midiaPanel = new JPanel();
                    midiaPanel.setLayout(new BorderLayout());
                    midiaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                    midiaPanel.add(infoPanel, BorderLayout.CENTER);

                    String capaUrl = rs.getString("capa");
                    try {
                        URL url = new URL(capaUrl);
                        ImageIcon icon = new ImageIcon(url);
                        JLabel imageLabel = new JLabel(icon);
                        midiaPanel.add(imageLabel, BorderLayout.WEST);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    infoPanel.add(new JLabel("ID: " + rs.getInt("idMidia")));
                    infoPanel.add(new JLabel("Nome: " + rs.getString("nome")));
                    infoPanel.add(new JLabel("Preço: " + String.format("%.2f",rs.getDouble("preco"))));
                    infoPanel.add(new JLabel("Classificação Indicativa: " + rs.getString("classIndicativa")));
                    infoPanel.add(new JLabel("Gênero: " + rs.getString("genero")));
                    infoPanel.add(new JLabel("Quantidade em Estoque: " + rs.getInt("qtdEstoque")));
                    infoPanel.add(new JLabel("Diretor: " + rs.getString("diretor")));
                    infoPanel.add(new JLabel("Duração: " + rs.getString("duracao")));
                    infoPanel.add(new JLabel("Elenco: " + rs.getString("elenco")));
                    infoPanel.add(new JLabel("Tipo: " + rs.getString("tipo")));

                    mainPanel.add(midiaPanel);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar filmes: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

	public void consultarFilme(String nome) {
		String dadosFilmes = "";
        try (Connection conecta = ConectaBD.conexao()) {
            String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, f.diretor, f.duracao, f.elenco, m.tipo "
                       + "FROM midia m "
                       + "INNER JOIN filme f ON m.idMidia = f.idFilme "
                       + "WHERE m.nome LIKE ? "
                       + "ORDER BY m.idMidia";
            
            try (PreparedStatement st = conecta.prepareStatement(sql)) {
                st.setString(1, "%" + nome + "%");
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        dadosFilmes += "ID: " + rs.getInt("idMidia") + "\n" 
                                     + "Nome: " + rs.getString("nome") + "\n"
                                     + "Preço: " + rs.getDouble("preco") + "\n"
                                     + "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n"
                                     + "Gênero: " + rs.getString("genero") + "\n"
                                     + "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n"
                                     + "Capa: " + rs.getString("capa") + "\n"
                                     + "Diretor: " + rs.getString("diretor") + "\n"
                                     + "Duração: " + rs.getString("duracao") + "\n"
                                     + "Elenco: " + rs.getString("elenco") + "\n"
                                     + "Tipo: " + rs.getString("tipo") + "\n\n";
                    }
                    JOptionPane.showMessageDialog(null, dadosFilmes);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar filme: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

	public void alterarFilme(int idFilme) {
		try (Connection conecta = ConectaBD.conexao()) {
            Filme filme = new Filme();
            filme.setNome(JOptionPane.showInputDialog("Digite o nome do filme: "));
            filme.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do filme: ")));
            filme.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do filme: "));
            filme.setGenero(JOptionPane.showInputDialog("Digite o gênero do filme: "));
            filme.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do filme: ")));
            filme.setCapa(JOptionPane.showInputDialog("Informe a URL da capa do filme: "));
            filme.setDuracao(JOptionPane.showInputDialog("Informe a duração do filme: "));

            int qtdElenco = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de atores no elenco: "));
            for (int i = 0; i < qtdElenco; i++) {
                filme.adicionarAtor(JOptionPane.showInputDialog("Informe o nome do ator"));
            }

            String elencoString = String.join(",", filme.getElenco());

            try {
                // Atualizar a tabela midia
                 st = conecta.prepareStatement(
                        "UPDATE midia SET nome = ?, preco = ?, classIndicativa = ?, genero = ?, qtdEstoque = ?, capa = ? WHERE idMidia = ?");
                st.setString(1, filme.getNome());
                st.setDouble(2, filme.getPreco());
                st.setString(3, filme.getClassIndicativa());
                st.setString(4, filme.getGenero());
                st.setInt(5, filme.getQtdEstoque());
                st.setString(6, filme.getCapa());
                st.setInt(7, idFilme);
                st.executeUpdate();
                st.close();

                // Atualizar a tabela filme
                st = conecta.prepareStatement(
                        "UPDATE filme SET duracao = ?, elenco = ? WHERE idFilme = ?");
                st.setString(1, filme.getDuracao());
                st.setString(2, elencoString);
                st.setInt(3, idFilme);
                st.executeUpdate();
                st.close();

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
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

	public void deletarFilme(int idFilme) {
		try (Connection conecta = ConectaBD.conexao()) {
            try {
                st = conecta.prepareStatement("DELETE FROM filme WHERE idFilme=?");
                st.setInt(1, idFilme);
                int resultadoFilme = st.executeUpdate();
                st.close();

                if (resultadoFilme == 0) {
                    JOptionPane.showMessageDialog(null, "Filme não encontrado");
                    return; // Se o filme não for encontrado, para a execução
                }

                // Depois, deletar da tabela midia
                st = conecta.prepareStatement("DELETE FROM midia WHERE idMidia=?");
                st.setInt(1, idFilme);
                int resultadoMidia = st.executeUpdate();
                st.close();

                // Verifica se a mídia foi removida
                if (resultadoMidia == 0)
                    JOptionPane.showMessageDialog(null, "Mídia não encontrada");
                else
                    JOptionPane.showMessageDialog(null, "O filme e a mídia de registro " + idFilme + " foram removidos com sucesso");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar filme: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
	
	public Filme obterUltimoFilmeAdicionado() {
	    Filme ultimoFilme = null;

	    try (Connection conecta = ConectaBD.conexao()) {
	    	try {
	    		st = conecta.prepareStatement("SELECT * FROM filme ORDER BY idFilme DESC LIMIT 1");
	    		ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	        	int idMidia = rs.getInt("idMidia");
	        	int id = rs.getInt("idFilme");
	            String nome = rs.getString("nome");
	            double preco = rs.getFloat("preco");
	            String classIndicativa = rs.getString("classIndicativa");
	            String genero = rs.getString("genero");
	            int qtdEstoque = rs.getInt("qtdEstoque");
	            String capa = rs.getString("capa");
	            String diretor = rs.getString("diretor");
	            String duracao = rs.getString("duracao");
	            String elencoBD = rs.getString("elenco");
	            String tipo = rs.getString("tipo");
	            
	            ArrayList<String> elenco = new ArrayList<>();
	            if (elencoBD != null && !elencoBD.isEmpty()) {
	                String[] atores = elencoBD.split(",");
	                elenco.addAll(Arrays.asList(atores));
	            }
	            
	            //id, nome, preco, classIndicativa, genero, qtdEstoque, capa, duracao, elenco
	            ultimoFilme = new Filme(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo, id, diretor, duracao, elenco);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());

	}
	    return ultimoFilme;
	}
}



