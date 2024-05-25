package model.dao;

import java.awt.BorderLayout;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.beans.Midia;

public class MidiaDAO {
	
	PreparedStatement st;

	public MidiaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Midia obterUltimaMidia() {
	    Midia ultimaMidia = new Midia();

	    try (Connection conecta = ConectaBD.conexao()) {
	    	try {
	    		st = conecta.prepareStatement("SELECT * FROM midia ORDER BY idMidia DESC LIMIT 1");
	    		ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	        	int idMidia = rs.getInt("idMidia");
	            String nome = rs.getString("nome");
	            double preco = rs.getFloat("preco");
	            String classIndicativa = rs.getString("classIndicativa");
	            String genero = rs.getString("genero");
	            int qtdEstoque = rs.getInt("qtdEstoque");
	            String capa = rs.getString("capa");
	            String tipo = rs.getString("tipo");

	            
	         
	            
	            //id, nome, preco, classIndicativa, genero, qtdEstoque, capa, duracao, elenco
	            ultimaMidia = new Midia(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());

	}
	    return ultimaMidia;
	}
	
	public void consultarMidia(String nome, JPanel mainPanel) {
	    mainPanel.removeAll(); // Limpa qualquer conteúdo anterior

	    try (Connection conecta = ConectaBD.conexao()) {
	        String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, m.tipo "
	                   + "FROM midia m "
	                   + "WHERE m.nome LIKE ? "
	                   + "ORDER BY m.idMidia";

	        try (PreparedStatement st = conecta.prepareStatement(sql)) {
	            st.setString(1, "%" + nome + "%");
	            try (ResultSet rs = st.executeQuery()) {
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
	                        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Adiciona espaço entre a imagem e as informações
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
	                    infoPanel.add(new JLabel("Tipo: " + rs.getString("tipo")));

	                    mainPanel.add(midiaPanel);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Erro ao consultar mídia: " + e.getMessage());
	    }

	    mainPanel.revalidate(); // Atualize a interface gráfica para refletir as mudanças
	    mainPanel.repaint();
	}
	
	
	
	public void exibirInformacoesMidiasSelecionadas(List<Integer> idsSelecionados, JPanel mainPanel) {
	    mainPanel.removeAll(); // Limpa qualquer conteúdo anterior

	    try (Connection conecta = ConectaBD.conexao()) {
	        StringBuilder sqlBuilder = new StringBuilder("SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, m.tipo ");
	        sqlBuilder.append("FROM midia m ");
	        sqlBuilder.append("WHERE m.idMidia IN (");
	        for (int i = 0; i < idsSelecionados.size(); i++) {
	            if (i > 0) {
	                sqlBuilder.append(",");
	            }
	            sqlBuilder.append("?");
	        }
	        sqlBuilder.append(") ORDER BY m.idMidia");

	        try (PreparedStatement st = conecta.prepareStatement(sqlBuilder.toString())) {
	            for (int i = 0; i < idsSelecionados.size(); i++) {
	                st.setInt(i + 1, idsSelecionados.get(i));
	            }

	            try (ResultSet rs = st.executeQuery()) {
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
	                        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Adiciona espaço entre a imagem e as informações
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
	                    infoPanel.add(new JLabel("Tipo: " + rs.getString("tipo")));

	                    mainPanel.add(midiaPanel);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Erro ao exibir informações das mídias selecionadas: " + e.getMessage());
	    }

	    mainPanel.revalidate(); // Atualize a interface gráfica para refletir as mudanças
	    mainPanel.repaint();
	}
	
	public static void atualizarEstoque(Midia midia) {
	    try (Connection conecta = ConectaBD.conexao();
	         PreparedStatement st = conecta.prepareStatement("UPDATE midia SET qtdEstoque = ? WHERE idMidia = ?")) {
	        st.setInt(1, midia.getQtdEstoque());
	        st.setInt(2, midia.getIdMidia());
	        st.executeUpdate();
	        
	        System.out.println(midia.getTipo());
	        System.out.println(midia.getIdMidia());

	        if (midia.getQtdEstoque() == 0 && midia.getTipo().equalsIgnoreCase("Jogo")) {
	            JogoDAO jogoDAO = new JogoDAO();
	            jogoDAO.deletarJogo(midia.getIdMidia());
	        }
	        
	        if (midia.getQtdEstoque() == 0 && midia.getTipo().equalsIgnoreCase("Filme")) {
	            FilmeDAO filmeDAO = new FilmeDAO();
	            filmeDAO.deletarFilme(midia.getIdMidia());
	        }
	        
	        if (midia.getQtdEstoque() == 0 && midia.getTipo().equalsIgnoreCase("Album")) {
	            AlbumDAO albumDAO = new AlbumDAO();
	            albumDAO.deletarAlbum(midia.getIdMidia());
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar a quantidade em estoque da mídia: " + e.getMessage());
	    }
	}

	public static Midia obterMidiaPorId(int idMidia) {
        Midia midia = new Midia();
        try (Connection conecta = ConectaBD.conexao();
             PreparedStatement st = conecta.prepareStatement("SELECT * FROM midia WHERE idMidia = ?")) {
            st.setInt(1, idMidia);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    midia = new Midia(
                        resultSet.getInt("idMidia"),
                        resultSet.getString("nome"),
                        resultSet.getDouble("preco"),
                        resultSet.getString("classIndicativa"),
                        resultSet.getString("genero"),
                        resultSet.getInt("qtdEstoque"),
                        resultSet.getString("capa"),
                        resultSet.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter a mídia do banco de dados: " + e.getMessage());
        }
        return midia;
    }
	
    public static void removerMidia(Midia midia) {
        try (Connection conecta = ConectaBD.conexao();
             PreparedStatement statement = conecta.prepareStatement("DELETE FROM midia WHERE idMidia = ?")) {
            statement.setInt(1, midia.getIdMidia());
            statement.executeUpdate();
                        
        } catch (SQLException e) {
            System.out.println("Erro ao remover a mídia do banco de dados: " + e.getMessage());
        }
    }
    
    
	
    
    
}
