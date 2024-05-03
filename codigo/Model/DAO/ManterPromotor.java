package DAO;

import javax.swing.*;

public class PromotorDAO {
  

  public void cadastrarPromotor(String nomePromotor, String nomeEmpresa, String cnpjEmpresa, String contatoEmpresa) {
    this.nomePromotor = JOptionPane.showInputDialog("Nome do Promotor: ");
    this.nomeEmpresa = JOptionPane.showInputDialog("Nome da Empresa: ");
    this.cnpjEmpresa = JOptionPane.showInputDialog("CNPJ da Empresa: ");
    this.contatoEmpresa = JOptionPane.showInputDialog("Contato da Empresa: ");
    
    try {
      Promotor promotor = new Promotor(nomePromotor, nomeEmpresa, cnpjEmpresa, contatoEmpresa);
    } catch (Exception e) {
      String erro = e.getMessage();
    }
    
  }

  public void listarIngressos() {

  }

  public void buscarPromotor(Promotor codPromotor){}

  public void atualizarIngressos(Promotor codPromotor) {

  }

  public void deletarPromotor(Promotor codPromotor) {

  }
}
