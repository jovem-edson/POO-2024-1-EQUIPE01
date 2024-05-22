package view;

import model.dao.*;
import javax.swing.JOptionPane;

public class Menu {
	private JogoDAO jogo;
	private AlbumDAO musica;


    public Menu() {
        this.jogo = new JogoDAO();
        this.musica = new AlbumDAO();
    }

    public void exibirMenu() {
        String escolha[] = {"1","2","3","4","5","6","7","8","9","10", "0"};
        Object opcao;
     

        do {
           
             opcao = JOptionPane.showInputDialog(null,"Escolha uma opção:\n" +
                    "1 - Cadastrar Jogo\n" +
                    "2 - Listar Jogos\n" +
                    "3 - Consultar Jogo\n" +
                    "4 - Alterar Jogo\n" +
                    "5 - Deletar Jogo\n" +
                    "6 - Cadastrar Música\n" +
                    "7 - Listar Músicas\n" +
                    "8 - Consultar Música\n" +
                    "9 - Alterar Música\n" +
                    "10 - Deletar Música\n" +
                    "0 - Sair",
               "Menu", JOptionPane.WARNING_MESSAGE, 
               null, escolha, escolha[0]);

            switch (String.valueOf(opcao)) {
                case "1":
                    jogo.cadastrarJogo();
                    break;
                case "2":
                    jogo.listarJogo();
                    break;
                case "3":
                    String nomeJogo = JOptionPane.showInputDialog("Digite o nome do jogo:");
                    jogo.consultarJogo(nomeJogo);
                    break;
                case "4":
                    int idJogoAlterar = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do jogo a ser alterado:"));
                    jogo.alterarJogo(idJogoAlterar);
                    break;
                case "5":
                    int idJogoDeletar = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do jogo a ser deletado:"));
                    jogo.deletarJogo(idJogoDeletar);
                    break;
                case "6":
                    musica.cadastrarMusica();
                    break;
                case "7":
                    musica.listarMusica();
                    break;
                case "8":
                    String tituloMusica = JOptionPane.showInputDialog("Digite o título da música:");
                    musica.consultarMusica(tituloMusica);
                    break;
                case "9":
                    int idMusicaAlterar = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da música a ser alterada:"));
                    musica.alterarMusica(idMusicaAlterar);
                    break;
                case "10":
                    int idMusicaDeletar = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da música a ser deletada:"));
                    musica.deletarMusica(idMusicaDeletar);
                    break;
                case "0":
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (String.valueOf(opcao).equals("0"));
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.exibirMenu();
    }
}
