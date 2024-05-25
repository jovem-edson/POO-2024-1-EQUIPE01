package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.beans.Album;
import model.beans.Filme;
import model.beans.Jogo;
import model.beans.Midia;
import model.dao.AlbumDAO;
import model.dao.FilmeDAO;
import model.dao.JogoDAO;
import model.dao.ListagemDAO;
import model.dao.MidiaDAO;

public class Home extends JFrame {
	
	AlbumDAO albumDAO = new AlbumDAO();
	FilmeDAO filmeDAO = new FilmeDAO();
	JogoDAO jogoDAO = new JogoDAO();

	
    private JRadioButton filmeRadioButton;
    private JRadioButton musicaRadioButton;
    private JRadioButton jogoRadioButton;


    public Home() {
    	MidiaDAO midiaDAO = new MidiaDAO();
    	Midia ultimaMidiaAdicionada = midiaDAO.obterUltimaMidia();
    	
        setTitle("Meu Aplicativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);

        // Painel principal com layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // Cabeçalho com layout BorderLayout
        JPanel headerPanel = new JPanel(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Barra de pesquisa dentro do cabeçalho
        JTextField searchField = new JTextField();
        searchField.setColumns(20);
        searchField.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(searchField, BorderLayout.EAST);

        // Barra de ferramentas colada ao cabeçalho
        JToolBar toolBar = new JToolBar();
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirOpcoesDeCadastro();
            }
        });
        toolBar.add(cadastrarButton);

        
        JButton btn2 = new JButton("Listar Mídias");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               exibirOpcoesDeListagem();
            }
        });
        toolBar.add(btn2);

        JButton btn3 = new JButton("Alterar Mídias");
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               exibirOpcoesDeAlteracao();
            }
        });
        toolBar.add(btn3);
        
        
        JButton btn4 = new JButton("Deletar Mídias");
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               exibirOpcoesDeExclusao();
            }
        });
        toolBar.add(btn4);
        
        headerPanel.add(toolBar, BorderLayout.SOUTH);
        
        
        
        
       
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 192));
        headerPanel.add(panel, BorderLayout.CENTER);
        
        JLabel titulo = new JLabel("GALAXY");
        titulo.setForeground(new Color(255, 255, 255));
        panel.add(titulo);
        
        JPanel footerPanel = new JPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        JLabel footerLabel = new JLabel("GALAXY© TODOS OS DIREITOS RESERVADOS");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerLabel);
        try {
            URL url = new URL(ultimaMidiaAdicionada.getCapa());
            ImageIcon icon = new ImageIcon(url);
            
            JPanel panel_1 = new JPanel();
            mainPanel.add(panel_1, BorderLayout.CENTER);
            
            JLabel IdLabel = new JLabel("ID:\r\n");
            
            JLabel exibicaoID = new JLabel(ultimaMidiaAdicionada.getIdMidia() + "\r\n");
            
            JLabel label = new JLabel("");
            
            JLabel NomeLabel = new JLabel("Nome:");
            
            JLabel exibicaoNome = new JLabel( ultimaMidiaAdicionada.getNome() + "\r\n");
            
            JLabel label_1 = new JLabel("");
            
            JLabel precoLabel = new JLabel("Preço:");
            
            JLabel exibicaoPreco = new JLabel(String.format("R$ %.2f", ultimaMidiaAdicionada.getPreco()));
            
            JLabel label_2 = new JLabel("");
            
            JLabel ClassIndLabel = new JLabel("ClassInd:");
            
            JLabel exibicaoClassInd = new JLabel(ultimaMidiaAdicionada.getClassIndicativa() + "\r\n");
            
            JLabel label_3 = new JLabel("");
            
            JLabel generoLabel = new JLabel("Gênero:");
            
            JLabel exibicaoGenero = new JLabel(ultimaMidiaAdicionada.getGenero() + "\r\n");
            
            JLabel label_4 = new JLabel("");
            
            JLabel estoqueLabel = new JLabel("Estoque:");
            
            JLabel exibicaoEstoque = new JLabel(ultimaMidiaAdicionada.getQtdEstoque() + "\r\n");
            
            JLabel label_5 = new JLabel("");
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(10, 60, 200, 200);
            GroupLayout gl_panel_1 = new GroupLayout(panel_1);
            gl_panel_1.setHorizontalGroup(
            	gl_panel_1.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_panel_1.createSequentialGroup()
            			.addContainerGap()
            			.addComponent(imageLabel)
            			.addPreferredGap(ComponentPlacement.RELATED)
            			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addComponent(estoqueLabel)
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addComponent(exibicaoEstoque, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
            					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addComponent(generoLabel)
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addComponent(exibicaoGenero, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
            					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addComponent(ClassIndLabel)
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addComponent(exibicaoClassInd, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
            					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addComponent(precoLabel)
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addComponent(exibicaoPreco, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
            					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addComponent(NomeLabel)
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addComponent(exibicaoNome, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
            					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addComponent(IdLabel)
            					.addPreferredGap(ComponentPlacement.UNRELATED)
            					.addComponent(exibicaoID, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
            					.addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
            );
            gl_panel_1.setVerticalGroup(
            	gl_panel_1.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_panel_1.createSequentialGroup()
            			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            				.addGroup(gl_panel_1.createSequentialGroup()
            					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            							.addComponent(exibicaoID, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            							.addComponent(IdLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            						.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            							.addComponent(exibicaoNome, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            							.addComponent(NomeLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            							.addComponent(exibicaoPreco, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            							.addComponent(precoLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            							.addComponent(exibicaoClassInd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            							.addComponent(ClassIndLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            							.addComponent(exibicaoGenero, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            							.addComponent(generoLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            					.addPreferredGap(ComponentPlacement.RELATED)
            					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            							.addComponent(exibicaoEstoque, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            							.addComponent(estoqueLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
            						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
            				.addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
            			.addContainerGap())
            );
            panel_1.setLayout(gl_panel_1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //homens, este aqui é para pesquisar através da barra de pesquisa
        //pegamos o texto inserido e passamos pra função de consultar filmes
        //trocar para pesqusiar midia
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = searchField.getText();
                if (!nome.isEmpty()) {
                    ListagemFrame<Midia> listagemFrame = new ListagemFrame<>("Resultado da Busca", new ListagemDAO<Midia>() {
                        @Override
                        public void listar(JPanel mainPanel) {
                        	midiaDAO.consultarMidia(nome, mainPanel);
                        }
                    });
                    listagemFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Home.this, "Digite um nome para pesquisar.");
                }
            }
        });
    }
        
    
    
    

    private void exibirOpcoesDeCadastro() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();

        filmeRadioButton = new JRadioButton("Filme");
        panel.add(filmeRadioButton, BorderLayout.NORTH);
        group.add(filmeRadioButton);

        musicaRadioButton = new JRadioButton("Album");
        panel.add(musicaRadioButton, BorderLayout.CENTER);
        group.add(musicaRadioButton);

        jogoRadioButton = new JRadioButton("Jogo");
        panel.add(jogoRadioButton, BorderLayout.SOUTH);
        group.add(jogoRadioButton);

        JOptionPane.showMessageDialog(this, panel, "Escolha o tipo de mídia", JOptionPane.PLAIN_MESSAGE);

        if (filmeRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Filme");
            filmeDAO.cadastrarFilme();
            dispose();
			Home home = new Home();
            home.setVisible(true);
            
        } else if (musicaRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Album");
            albumDAO.cadastrarAlbum();
            dispose();
			Home home = new Home();
            home.setVisible(true);
            
        } else if (jogoRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Jogo");
            jogoDAO.cadastrarJogo();
            dispose();
			Home home = new Home();
            home.setVisible(true);
        
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma opção!");
        }
    }
    
    private void exibirOpcoesDeListagem() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();

        filmeRadioButton = new JRadioButton("Filme");
        panel.add(filmeRadioButton, BorderLayout.NORTH);
        group.add(filmeRadioButton);

        musicaRadioButton = new JRadioButton("Album");
        panel.add(musicaRadioButton, BorderLayout.CENTER);
        group.add(musicaRadioButton);

        jogoRadioButton = new JRadioButton("Jogo");
        panel.add(jogoRadioButton, BorderLayout.SOUTH);
        group.add(jogoRadioButton);

        JOptionPane.showMessageDialog(this, panel, "Escolha o tipo de mídia", JOptionPane.PLAIN_MESSAGE);

        if (filmeRadioButton.isSelected()) {
            ListagemFrame<Filme> listagemFrame = new ListagemFrame<>("Listagem de Filmes", filmeDAO);
            listagemFrame.setVisible(true);        
        } else if (musicaRadioButton.isSelected()) {
            ListagemFrame<Album> listagemFrame = new ListagemFrame<>("Listagem de Álbuns", albumDAO);
            listagemFrame.setVisible(true); 
        } else if (jogoRadioButton.isSelected()) {
            ListagemFrame<Jogo> listagemFrame = new ListagemFrame<>("Listagem de Jogos", jogoDAO);
            listagemFrame.setVisible(true); 
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma opção!");
            return;
        }    
        }
    
    private void exibirOpcoesDeAlteracao() {
    	JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();

        filmeRadioButton = new JRadioButton("Filme");
        panel.add(filmeRadioButton, BorderLayout.NORTH);
        group.add(filmeRadioButton);

        musicaRadioButton = new JRadioButton("Album");
        panel.add(musicaRadioButton, BorderLayout.CENTER);
        group.add(musicaRadioButton);

        jogoRadioButton = new JRadioButton("Jogo");
        panel.add(jogoRadioButton, BorderLayout.SOUTH);
        group.add(jogoRadioButton);

        JOptionPane.showMessageDialog(this, panel, "Escolha o tipo de mídia", JOptionPane.PLAIN_MESSAGE);

        if (filmeRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Filme");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do filme:"));
            filmeDAO.alterarFilme(id);
            dispose();
			Home home = new Home();
            home.setVisible(true);
            
        } else if (musicaRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Album");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do album:"));
            albumDAO.alterarAlbum(id);
            dispose();
			Home home = new Home();
            home.setVisible(true);
            
        } else if (jogoRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Jogo");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do jogo:"));
            jogoDAO.alterarJogo(id);
            dispose();
			Home home = new Home();
            home.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma opção!");
        }
    	
    }
    
    private void exibirOpcoesDeExclusao() {
    	JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();

        filmeRadioButton = new JRadioButton("Filme");
        panel.add(filmeRadioButton, BorderLayout.NORTH);
        group.add(filmeRadioButton);

        musicaRadioButton = new JRadioButton("Album");
        panel.add(musicaRadioButton, BorderLayout.CENTER);
        group.add(musicaRadioButton);

        jogoRadioButton = new JRadioButton("Jogo");
        panel.add(jogoRadioButton, BorderLayout.SOUTH);
        group.add(jogoRadioButton);

        JOptionPane.showMessageDialog(this, panel, "Escolha o tipo de mídia", JOptionPane.PLAIN_MESSAGE);

        if (filmeRadioButton.isSelected()) {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Opção selecionada: Filme\n\nInforme o ID do filme:"));

            filmeDAO.deletarFilme(id);
            dispose();
			Home home = new Home();
            home.setVisible(true);
            
        } else if (musicaRadioButton.isSelected()) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Opção selecionada: Album\n\nInforme o ID do album:"));

            albumDAO.deletarAlbum(id);
            dispose();
			Home home = new Home();
            home.setVisible(true);
            
        } else if (jogoRadioButton.isSelected()) {
        	int id = Integer.parseInt(JOptionPane.showInputDialog("Opção selecionada: Jogo\n\nInforme o ID do jogo:"));

            jogoDAO.deletarJogo(id);
            dispose();
			Home home = new Home();
            home.setVisible(true);
        
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma opção!");
        }
    	
    }
   



}
