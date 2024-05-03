package DAO;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {

  Connection conecta;
  PreparedStatement st;

  public ClienteDAO(){
    try {
      conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/Senac", "root", "");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void cadastrarCliente() {
    Cliente cliente = new Cliente();
    cliente.setNomeCliente(JOptionPane.showInputDialog("Digite o nome do cliente: "));
    cliente.setCpfCliente(JOptionPane.showInputDialog("Digite o CPF do cliente: "));
    cliente.setTelefoneCliente(JOptionPane.showInputDialog("Digite o telefone do cliente: "));
    cliente.setEmailCliente(JOptionPane.showInputDialog("Digite o email do cliente: "));
    
    try {
      st = conecta.prepareStatement("INSERT INTO cliente VALUES (?,?,?)");
      st.setString(1, Cliente.getNome());
      st.setString(2, Cliente.getCpf());
      st.setString(3, Cliente.getEmail());

      //Executando o comando INSERT
      st.executeUpdate();
      System.out.println("Cliente cadastrado com sucesso!");
    } catch (Exception e) {
      String erro = e.getMessage();
      if (erro.contains("Duplicate entry")){
        System.out.println("Cliente já cadastrado!");
      } else {
        System.out.println("Erro: " + erro + "ao cadastrar cliente!");
      }

  }
  }

  public void listarClientes() {
    try {
      st = conecta.prepareStatement("SELECT * FROM cliente ORDER BY idCliente");
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        System.out.println("Nome: " + rs.getString("nome"));
        System.out.println("CPF: " + rs.getString("cpf"));
        System.out.println("Telefone: " + rs.getString("telefone"));
        System.out.println("Email: " + rs.getString("email"));
        System.out.println("---------------------------");
      }
    } catch(Exception e) {
      String erro = e.getMessage();
      System.out.println("Erro: " + erro + " ao listar clientes!");
    }

  }

  public void buscarCliente(Cliente codCliente) {

    try{
    st = conecta.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ? ORDER BY idCliente");
    st.setString(1, "%" + Cliente.getNome() + "%");
    ResultSet rs = st.executeQuery();

    while (rs.next()) {
      System.out.println("Nome: " + rs.getString("nome"));
      System.out.println("CPF: " + rs.getString("cpf"));
      System.out.println("Telefone: " + rs.getString("telefone"));
      System.out.println("Email: " + rs.getString("email"));
      System.out.println("---------------------------");
    } 
  }catch (Exception e) {
    System.out.println("Erro: " + e.getMessage() + " ao buscar cliente!");
    }
  }
    

  public void atualizarCliente(Cliente codCliente) {
    

  }

  public void deletarCliente(Cliente codCliente) {

  }
}