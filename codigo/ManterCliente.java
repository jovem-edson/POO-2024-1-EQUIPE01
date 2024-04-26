package codigo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ManterCliente {
  list<Cliente> clientes = new ArrayList<>();
  
  public void cadastrarCliente() {
    Cliente cliente = new Cliente();
    cliente.setNomeCliente(JOptionPane.showInputDialog("Digite o nome do cliente: "));
    cliente.setCpfCliente(JOptionPane.showInputDialog("Digite o CPF do cliente: "));
    cliente.setTelefoneCliente(JOptionPane.showInputDialog("Digite o telefone do cliente: "));
    cliente.setEmailCliente(JOptionPane.showInputDialog("Digite o email do cliente: "));

    clientes.add(cliente);

  }

  public void listarClientes() {
    StringBuilder saida = new StringBuilder("Clientes\n");
    for(Evento e : eventos) {
      saida.append(e).append("\n"); 
    }
    JOptionPane.showMessageDialog(null, output.toString());

  }

  public void selecionarCliente(Cliente codCliente) {

  }

  public void atualizarCliente(Cliente codCliente) {
    

  }

  public void deletarCliente(Cliente codCliente) {

  }
}