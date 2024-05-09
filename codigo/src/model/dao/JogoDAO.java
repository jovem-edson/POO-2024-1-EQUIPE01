package model.dao;

import model.beans.Jogo;
import java.sql.*;
import javax.swing.JOptionPane;

public class JogoDAO {
	
	PreparedStatement st;
	
	
	public void cadastrarJogo() {
        try (Connection conecta = ConectaBD.conexao()) {
        Jogo jogo = new Jogo();
        jogo.setNome(JOptionPane.showInputDialog("Digite o nome do jogo: "));
        jogo.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do jogo: ")));
        jogo.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do jogo: "));
        jogo.setGenero(JOptionPane.showInputDialog("Digite o gênero do jogo: "));
        jogo.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do jogo: ")));
        jogo.setIsOnline(Boolean.parseBoolean(JOptionPane.showInputDialog("O jogo é online? (true/false): ")));

        try {
            st = conecta.prepareStatement("INSERT INTO jogo (nome, preco, classIndicativa, genero, qtdEstoque, isOnline) VALUES (?,?,?,?,?,?)");
            st.setString(1, jogo.getNome());
            st.setDouble(2, jogo.getPreco());
            st.setString(3, jogo.getClassIndicativa());
            st.setString(4, jogo.getGenero());
            st.setInt(5, jogo.getQtdEstoque());
            st.setBoolean(6, jogo.getIsOnline());

            // Executando o comando INSERT
            st.executeUpdate();
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

    public void listarJogo() {
        try (Connection conecta = ConectaBD.conexao()) {
        String dadosJogos = "";
        try {
            st = conecta.prepareStatement("SELECT * FROM jogo ORDER BY idJogo");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                dadosJogos += "ID: " + rs.getInt("idJogo") + "\n" +
                        "Nome: " + rs.getString("nome") + "\n" +
                        "Preço: " + rs.getDouble("preco") + "\n" +
                        "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n" +
                        "Gênero: " + rs.getString("genero") + "\n" +
                        "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n" +
                        "Online: " + rs.getBoolean("isOnline") + "\n\n";
            }
            JOptionPane.showMessageDialog(null, dadosJogos);
        } catch (SQLException e) {
            String erro = e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro ao listar jogos: " + erro);
        }
    
    } catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	}
}

    public void consultarJogo(String nome) {
        try (Connection conecta = ConectaBD.conexao()) {
        String dadosJogos = "";
        try {
            st = conecta.prepareStatement("SELECT * FROM jogo WHERE nome LIKE ? ORDER BY idJogo");
            st.setString(1, "%" + nome + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                dadosJogos += "ID: " + rs.getInt("idJogo") + "\n" +
                        "Nome: " + rs.getString("nome") + "\n" +
                        "Preço: " + rs.getDouble("preco") + "\n" +
                        "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n" +
                        "Gênero: " + rs.getString("genero") + "\n" +
                        "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n" +
                        "Online: " + rs.getBoolean("isOnline") + "\n\n";
            }
            JOptionPane.showMessageDialog(null, dadosJogos);
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
        jogo.setIsOnline(Boolean.parseBoolean(JOptionPane.showInputDialog("O jogo é online? (true/false): ")));
        try {
            st = conecta.prepareStatement("UPDATE jogo SET nome = ?, preco = ?, classIndicativa = ?, genero = ?, qtdEstoque = ?, isOnline = ? WHERE idJogo = ?");
            st.setString(1, jogo.getNome());
            st.setDouble(2, jogo.getPreco());
            st.setString(3, jogo.getClassIndicativa());
            st.setString(4, jogo.getGenero());
            st.setInt(5, jogo.getQtdEstoque());
            st.setBoolean(6, jogo.getIsOnline());
            st.setInt(7, idJogo);
            // Executando o comando UPDATE
            st.executeUpdate();
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
		JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	}
}

    public void deletarJogo(int idJogo) {
        try (Connection conecta = ConectaBD.conexao()) {
        try {
            st = conecta.prepareStatement("DELETE FROM jogo WHERE idJogo=?");
            st.setInt(1, idJogo);

            // Executa o comando DELETE
            int resultado = st.executeUpdate();

            // Verifica se o jogo foi removido
            if (resultado == 0)
                JOptionPane.showMessageDialog(null, "Jogo não encontrado");
            else
                JOptionPane.showMessageDialog(null, "O jogo de registro " + idJogo + " foi removido com sucesso");

        } catch (SQLException e) {
            String erro = e.getMessage();
            JOptionPane.showMessageDialog(null, "Erro ao deletar jogo: " + erro);
        }
        
    } catch (SQLException e) {
	JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
}
}

}
