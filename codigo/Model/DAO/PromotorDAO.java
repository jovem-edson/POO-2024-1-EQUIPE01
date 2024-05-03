package DAO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotorDAO {
  Connection conecta;
  PreparedStatement st;conecta=DriverManager.getConnection("jdbc:mysql://localhost:3306/Senac","root","");

  public void cadastrarPromotor() {
    Promotor promotor = new Promotor();
    promotor.setNomePromotor(JOptionPane.showInputDialog("Digite o nome do promotor: "));
    promotor.setNomeEmpresa(JOptionPane.showInputDialog("Digite o nome da empresa: "));
    promotor.setCnpjEmpresa(JOptionPane.showInputDialog("Digite o CNPJ da empresa: "));
    promotor.setContatoEmpresa(JOptionPane.showInputDialog("Digite o contato da empresa: "));
  
  try {
    st conecta.prepareStatement("INSERT INTO promotor VALUES (?,?,?,?)");
    st.setString(1, Promotor.getNomePromotor());
    st.setString(2, Promotor.getNomeEmpresa());
    st.setString(3, Promotor.getCnpjEmpresa());
    st.setString(4, Promotor.getContatoEmpresa());

    st.executeUpdate();
    System.out.println("Promotor cadastrado com sucesso!");
  } catch (Exception e) {
    String erro = e.getMessage();
    if (erro.contains("Duplicate entry")){
      System.out.println("Promotor já cadastrado!");
    } else {
      System.out.println("Erro: " + erro + "ao cadastrar promotor!");
    }
  }
}

  public void listarIngressos() {
    // for () {}

  }

  public void buscarPromotor(Promotor codPromotor) {
  }

  public void atualizarIngressos(Promotor codPromotor) {

  }

  public void deletarPromotor(Promotor codPromotor) {
    cn = conexao.openDB();
    int codProm = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do promotor que deseja deletar: "));
    try {
      int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar o promotor " + codProm);
      if (resp == 0) {
        st.executeUpdate("DELETE FROM promotores WHERE codPromotor = " + codProm);
        cn.commit();
      } else {
        JOptionPane.showMessageDialog(null, "O promotor de código " + codProm + " não foi deletado.");
      }
    } catch (Exception e) {
      String erro = e.getMessage();
    } finally {
      st.close();
      cn.close();
    }
  }
}
