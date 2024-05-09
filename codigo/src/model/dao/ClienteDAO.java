package model.dao;

import model.beans.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;

public class ClienteDAO {
	
	  PreparedStatement st;

	  public void cadastrarCliente() {
	        try (Connection conecta = ConectaBD.conexao()) {
	        Cliente cliente = new Cliente();
	        cliente.setNome(JOptionPane.showInputDialog("Digite o nome do cliente: "));
	        cliente.setCpf(JOptionPane.showInputDialog("Digite o CPF do cliente: "));
	        cliente.setEmail(JOptionPane.showInputDialog("Digite o email do cliente: "));

	        try {
	            st = conecta.prepareStatement("INSERT INTO cliente (nome, cpf, email) VALUES (?,?,?)");
	            st.setString(1, cliente.getNome());
	            st.setString(2, cliente.getCpf());
	            st.setString(3, cliente.getEmail());

	            // Executando o comando INSERT
	            st.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
	        } catch (SQLException e) {
	            String erro = e.getMessage();
	            if (erro.contains("Duplicate entry")) {
	                JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
	            } else {
	                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + erro);
	            }
	        }
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	    }
	  }
	  
	    public void listarCliente() {
	        try (Connection conecta = ConectaBD.conexao()) {
	        String dadosClientes = "";
	        try {
	            st = conecta.prepareStatement("SELECT * FROM cliente ORDER BY idCliente");
	            ResultSet rs = st.executeQuery();
	            while (rs.next()) {
	                dadosClientes += "ID: " + rs.getInt("idCliente") + "\n" +
	                        "Nome: " + rs.getString("nome") + "\n" +
	                        "CPF: " + rs.getString("cpf") + "\n" +
	                        "Email: " + rs.getString("email") + "\n\n";
	            }
	            JOptionPane.showMessageDialog(null, dadosClientes);
	        } catch (SQLException e) {
	            String erro = e.getMessage();
	            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + erro);
	        }
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	    }
	    }

	    public void consultarCliente(String nome) {
	        try (Connection conecta = ConectaBD.conexao()) {
	        String dadosClientes = "";
	        try {
	            st = conecta.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ? ORDER BY idCliente");
	            st.setString(1, "%" + nome + "%");
	            ResultSet rs = st.executeQuery();
	            while (rs.next()) {
	                dadosClientes += "ID: " + rs.getInt("idCliente") + "\n" +
	                        "Nome: " + rs.getString("nome") + "\n" +
	                        "CPF: " + rs.getString("cpf") + "\n" +
	                        "Email: " + rs.getString("email") + "\n\n";
	            }
	            JOptionPane.showMessageDialog(null, dadosClientes);
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
	        }
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	    }
}

	    public void alterarCliente(int idCliente) {
	        try (Connection conecta = ConectaBD.conexao()) {
	        Cliente cliente = new Cliente();
	        cliente.setNome(JOptionPane.showInputDialog("Digite o nome do cliente: "));
	        cliente.setCpf(JOptionPane.showInputDialog("Digite o CPF do cliente: "));
	        cliente.setEmail(JOptionPane.showInputDialog("Digite o email do cliente: "));
	        try {
	            st = conecta.prepareStatement("UPDATE cliente SET nome = ?, cpf = ?, email = ? WHERE idCliente = ?");
	            st.setString(1, cliente.getNome());
	            st.setString(2, cliente.getCpf());
	            st.setString(3, cliente.getEmail());
	            st.setInt(4, idCliente);
	            // Executando o comando UPDATE
	            st.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
	        } catch (SQLException e) {
	            String erro = e.getMessage();
	            if (erro.contains("Duplicate entry")) {
	                JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
	            } else {
	                JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + erro);
	            }
	        }
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	    }
	    }

	    public void deletarCliente(int idCliente) {
	        try (Connection conecta = ConectaBD.conexao()) {
	        try {
	            st = conecta.prepareStatement("DELETE FROM cliente WHERE idCliente=?");
	            st.setInt(1, idCliente);

	            // Executa o comando DELETE
	            int resultado = st.executeUpdate();

	            // Verifica se o cliente foi removido
	            if (resultado == 0)
	                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
	            else
	                JOptionPane.showMessageDialog(null, "O cliente de registro " + idCliente + " foi removido com sucesso");

	        } catch (SQLException e) {
	            String erro = e.getMessage();
	            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + erro);
	        }
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, "Erro ao conectat ao banco de dados: " + e.getMessage());
	    }
	    }
	    
	}
