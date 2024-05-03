package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ManterEvento {
  list<Evento> eventos = new ArrayList<>();
  
  public void cadastrarEvento() {
    Evento evento = new Evento();
    evento.setNomeEvento(JOptionPane.showInputDialog("Digite o nome do evento: "));
    evento.setDataEvento(JOptionPane.showInputDialog("Digite a data do evento: "));
    evento.setHoraEvento(JOptionPane.showInputDialog("Digite a hora do evento: "));
    evento.setLocalEvento(JOptionPane.showInputDialog("Digite o local do evento: "));
    evento.setTipoEvento(JOptionPane.showInputDialog("Digite o tipo do evento: "));
    evento.setCodEvento(Integer.parseInt(JOptionPane.showInputDialog("Digite o código do evento: ")));

    eventos.add(evento);
    
    JOptionPane.showMessageDialog(null, "Evento cadastrado com sucesso!");

  }

  public void listarEventos() {
    StringBuilder saida = new StringBuilder("Eventos\n");
    for(Evento e : eventos) {
      saida.append(e).append("\n"); 
    }
    JOptionPane.showMessageDialog(null, output.toString());
  }

  public void buscarEvento(Evento evento){
    
  }


  
//Método para atualizar puxando da classe bucarEventos
  public void atualizarEvento(int codEvento) {
    Evento evento = buscarEvento(codEvento);
    if(evento != null) {
      evento.setNomeEvento(JOptionPane.showInputDialog("Digite o novo nome do evento: "));
      evento.setDataEvento(JOptionPane.showInputDialog("Digite a nova data do evento: "));
      evento.setHoraEvento(JOptionPane.showInputDialog("Digite a nova hora do evento: "));
      evento.setLocalEvento(JOptionPane.showInputDialog("Digite o novo local do evento: "));
      evento.setTipoEvento(JOptionPane.showInputDialog("Digite o novo tipo do evento: "));
      evento.setCodEvento(Integer.parseInt(JOptionPane.showInputDialog("Digite o novo código do evento: ")));

      JOptionPane.showMessageDialog(null, "Evento atualizado com sucesso!");
    } else {
      JOptionPane.showMessageDialog(null, "Evento não encontrado!");
    }

  }

  public void deletarEvento(int codEvento) {
    Evento evento = buscarEvento(codEvento);
    if(evento != null) {
      eventos.remove(evento);
      JOptionPane.showMessageDialog(null, "Evento deletado com sucesso!");
    } else {
      JOptionPane.showMessageDialog(null, "Evento não encontrado!");
    }
    
    

  }
}