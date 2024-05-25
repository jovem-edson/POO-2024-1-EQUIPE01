package model.dao;

import model.beans.Jogo;

import java.awt.BorderLayout;
import java.net.URL;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JogoDAO implements ListagemDAO<Jogo>{
	
	PreparedStatement st;
	ResultSet rs;
	
	
	public void cadastrarJogo() {
        try (Connection conecta = ConectaBD.conexao()) {
        Jogo jogo = new Jogo();
        jogo.setNome(JOptionPane.showInputDialog("Digite o nome do jogo: "));
        jogo.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do jogo: ")));
        jogo.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do jogo: "));
        jogo.setGenero(JOptionPane.showInputDialog("Digite o gênero do jogo: "));
        jogo.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do jogo: ")));
		jogo.setCapa(JOptionPane.showInputDialog("Informe a URL da capa do jogo: "));
        jogo.setIsOnline(Boolean.parseBoolean(JOptionPane.showInputDialog("O jogo é online? (true/false): ")));

        try {
        	conecta.setAutoCommit(false); //desativando commit automático
			
			
			String sqlMidia = "INSERT INTO midia (nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo) VALUES (?,?,?,?,?,?,?)";
			st = conecta.prepareStatement(sqlMidia, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, jogo.getNome());
			st.setDouble(2, jogo.getPreco());
			st.setString(3, jogo.getClassIndicativa());
			st.setString(4, jogo.getGenero());
			st.setInt(5, jogo.getQtdEstoque());
			st.setString(6, jogo.getCapa());
			st.setString(7, "jogo");
			st.executeUpdate();
			
			//obtendo ID gerado
			rs = st.getGeneratedKeys();
			int lastId = 0;
			if (rs.next()) {
				lastId = rs.getInt(1);
			}
			
			rs.close();
			st.close();
			
			//Inserindo os dados na tabela jogo a partir do ID obtido ali em cima
			
			String sqlJogo = "INSERT INTO jogo (idjogo, isOnline) VALUES (?,?)";
			st = conecta.prepareStatement(sqlJogo);
			st.setInt(1, lastId);
			st.setBoolean(2, jogo.getIsOnline());
			
            // Executando o comando INSERT
            st.executeUpdate();
			conecta.commit();

            JOptionPane.showMessageDialog(null, "Jogo cadastrado com sucesso!");
        } catch (SQLException e) {
            String erro = e.getMessage();
            if (erro.contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Jogo já cadastrado!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar jogo: " + erro);
            }
        }
    
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	}
} 

	@Override
	public void listar(JPanel mainPanel) {
        try (Connection conecta = ConectaBD.conexao()) {
            String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, j.isOnline, m.tipo\r\n"
            		+ "FROM midia m\r\n"
            		+ "INNER JOIN jogo j ON m.idMidia = j.idJogo;";
            
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
                    infoPanel.add(new JLabel("Quantidade em Estoque: " + rs.getString("qtdEstoque")));
                    infoPanel.add(new JLabel("Online: " + rs.getBoolean("isOnline")));
                    infoPanel.add(new JLabel("Tipo: " + rs.getString("tipo")));

                    mainPanel.add(midiaPanel);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar jogos: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
	
    public void consultarJogo(String nome) {
    	String dadosJogos = "";
        try (Connection conecta = ConectaBD.conexao()) {
            String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, j.isOnline, m.tipo\r\n"
            		+ "FROM midia m\r\n"
            		+ "INNER JOIN jogo j ON m.idMidia = j.idJogo;"
            		+ "WHERE m.nome LIKE ?"
            		+ "ORDER BY m.idMidia";
            
            try (PreparedStatement st = conecta.prepareStatement(sql)) {
                st.setString(1, "%" + nome + "%");

                try (ResultSet rs = st.executeQuery()) {
                
                while (rs.next()) {
                    dadosJogos += "ID: " + rs.getInt("idMidia") + "\n" 
                                 + "Nome: " + rs.getString("nome") + "\n"
                                 + "Preço: " + rs.getDouble("preco") + "\n"
                                 + "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n"
                                 + "Gênero: " + rs.getString("genero") + "\n"
                                 + "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n"
                                 + "Capa: " + rs.getString("capa") + "\n"
                                 + "Online: " + rs.getBoolean("isOnline") + "\n"
                                 + "Tipo: " + rs.getString("tipo") + "\n\n";
                }
            JOptionPane.showMessageDialog(null, dadosJogos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar jogo: " + e.getMessage());
        }
    
    } catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	}
}

    public void alterarJogo(int idJogo) {
    	try (Connection conecta = ConectaBD.conexao()) {
            Jogo jogo = new Jogo();
            jogo.setNome(JOptionPane.showInputDialog("Digite o nome do jogo: "));
            jogo.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do jogo: ")));
            jogo.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do jogo: "));
            jogo.setGenero(JOptionPane.showInputDialog("Digite o gênero do jogo: "));
            jogo.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do jogo: ")));
            jogo.setCapa(JOptionPane.showInputDialog("Digite o URL da capa do jogo: "));
            jogo.setIsOnline(Boolean.parseBoolean(JOptionPane.showInputDialog("O jogo é online? (true/false): ")));

            try {
                // Atualizar a tabela midia
                PreparedStatement stMidia = conecta.prepareStatement(
                        "UPDATE midia SET nome = ?, preco = ?, classIndicativa = ?, genero = ?, qtdEstoque = ?, capa = ? WHERE idMidia = ?");
                stMidia.setString(1, jogo.getNome());
                stMidia.setDouble(2, jogo.getPreco());
                stMidia.setString(3, jogo.getClassIndicativa());
                stMidia.setString(4, jogo.getGenero());
                stMidia.setInt(5, jogo.getQtdEstoque());
                stMidia.setString(6, jogo.getCapa());
                stMidia.setInt(7, idJogo);
                stMidia.executeUpdate();
                stMidia.close();

                // Atualizar a tabela jogo
                PreparedStatement stJogo = conecta.prepareStatement(
                        "UPDATE jogo SET isOnline = ? WHERE idJogo = ?");
                stJogo.setBoolean(1, jogo.getIsOnline());
                stJogo.setInt(2, idJogo);
                stJogo.executeUpdate();
                stJogo.close();

                JOptionPane.showMessageDialog(null, "Jogo atualizado com sucesso!");
            } catch (SQLException e) {
                String erro = e.getMessage();
                if (erro.contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(null, "Jogo já cadastrado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar jogo: " + erro);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void deletarJogo(int idJogo) {
    	try (Connection conecta = ConectaBD.conexao()) {
            try {
                // Deletar da tabela jogo
                st = conecta.prepareStatement("DELETE FROM jogo WHERE idJogo = ?");
                st.setInt(1, idJogo);

                // Executa o comando DELETE na tabela jogo
                int resultadoJogo = st.executeUpdate();
                st.close();

                if (resultadoJogo == 0) {
                    JOptionPane.showMessageDialog(null, "Jogo não encontrado");
                } else {
                    // Deletar da tabela midia
                    st = conecta.prepareStatement("DELETE FROM midia WHERE idMidia = ?");
                    st.setInt(1, idJogo);

                    // Executa o comando DELETE na tabela midia
                    int resultadoMidia = st.executeUpdate();
                    st.close();

                    // Verifica se o midia foi removido
                    if (resultadoMidia == 0) {
                        JOptionPane.showMessageDialog(null, "Mídia não encontrada");
                    } else {
                        JOptionPane.showMessageDialog(null, "O jogo de registro " + idJogo + " foi removido com sucesso");
                    }
                }

            } catch (SQLException e) {
                String erro = e.getMessage();
                JOptionPane.showMessageDialog(null, "Erro ao deletar jogo: " + erro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
    
    public Jogo obterUltimoJogoAdicionado() {
	    Jogo ultimoJogo = null;

	    try (Connection conecta = ConectaBD.conexao()) {
	    	try {
	    		st = conecta.prepareStatement("SELECT * FROM jogo ORDER BY idAlbum DESC LIMIT 1");
	    		ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	        	int idMidia = rs.getInt("idMidia");
	        	int id = rs.getInt("idJogo");
	            String nome = rs.getString("nome");
	            double preco = rs.getFloat("preco");
	            String classIndicativa = rs.getString("classIndicativa");
	            String genero = rs.getString("genero");
	            int qtdEstoque = rs.getInt("qtdEstoque");
	            String capa = rs.getString("capa");
	            Boolean isOnline = rs.getBoolean("isOnline");
	            String tipo = rs.getString("tipo");
	            
	            
	            //id, nome, preco, classIndicativa, genero, qtdEstoque, capa, duracao, elenco
	            ultimoJogo = new Jogo(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo, id, isOnline);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());

	}
	    return ultimoJogo;
	}

}
