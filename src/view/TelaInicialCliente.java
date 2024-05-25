package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import model.beans.Album;
import model.beans.Boleto;
import model.beans.Carrinho;
import model.beans.Cartao;
import model.beans.Filme;
import model.beans.Jogo;
import model.beans.Midia;
import model.beans.Pagamento;
import model.beans.Pix;
import model.dao.AlbumDAO;
import model.dao.FilmeDAO;
import model.dao.JogoDAO;
import model.dao.ListagemDAO;
import model.dao.MidiaDAO;
import model.dao.UsuarioDAO;
import model.beans.Usuario;
import java.util.List;
import java.util.ArrayList;

public class TelaInicialCliente extends JFrame {
	private Usuario usuario;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TelaInicialCliente() {

		MidiaDAO midiaDAO = new MidiaDAO();
		Midia ultimaMidiaAdicionada = midiaDAO.obterUltimaMidia();

		setTitle("Tela Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 900);

		// Painel principal
		JPanel mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);

		// Cabeçalho
		JPanel headerPanel = new JPanel(new BorderLayout());
		mainPanel.add(headerPanel, BorderLayout.NORTH);

		// Barra de pesquisa dentro do cabeçalho
		JTextField searchField = new JTextField();
		searchField.setColumns(20);
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(searchField, BorderLayout.EAST);

		// Barra de opções abaixo do cabeçalho
		JToolBar optionsToolBar = new JToolBar();
		optionsToolBar.setRollover(true);
		optionsToolBar.setFloatable(false);
		JButton midiaButton = new JButton("Ver Mídias");
		midiaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibirOpcoesDeListagem();
			}
		});

		JButton perfilButton = new JButton("Perfil");
		perfilButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			exibirOpcoesDePerfil();
		}
		});
		
		optionsToolBar.add(midiaButton);
		optionsToolBar.add(perfilButton);
		headerPanel.add(optionsToolBar, BorderLayout.WEST);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(128, 128, 192));
		headerPanel.add(panel, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("GALAXY");
        titulo.setForeground(new Color(255, 255, 255));
        panel.add(titulo);

		// Painel central para exibir a ultima mídia e informações
		JPanel centerPanel = new JPanel(new BorderLayout());
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// Carregando imagem da mídia
		JLabel movieLabel = null;
		try {
			URL url = new URL(ultimaMidiaAdicionada.getCapa());
			ImageIcon movieImage = new ImageIcon(url);
			movieLabel = new JLabel(movieImage);
		} catch (Exception e) {
			e.printStackTrace();
			movieLabel = new JLabel("Imagem não disponível");
		}

		// Painel para imagem e informações
		JPanel mediaPanel = new JPanel();
		mediaPanel.setLayout(new BoxLayout(mediaPanel, BoxLayout.X_AXIS));
		centerPanel.add(mediaPanel, BorderLayout.CENTER);

		// Adicionando imagem ao painel
		mediaPanel.add(movieLabel);

		// Painel para informações da mídia
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		mediaPanel.add(infoPanel);

		// Simulando informações da mídia
		JLabel idLabel = new JLabel("ID: " + ultimaMidiaAdicionada.getIdMidia());
		JLabel nameLabel = new JLabel("Nome: " + ultimaMidiaAdicionada.getNome());
		JLabel priceLabel = new JLabel("Preço: " + String.format("%.2f", ultimaMidiaAdicionada.getPreco()));
		JLabel classIndLabel = new JLabel("ClassInd: " + ultimaMidiaAdicionada.getClassIndicativa());
		JLabel genreLabel = new JLabel("Gênero: " + ultimaMidiaAdicionada.getGenero());
		JLabel stockLabel = new JLabel("Estoque: " + ultimaMidiaAdicionada.getQtdEstoque());
		JLabel typeLabel = new JLabel("Tipo: " + ultimaMidiaAdicionada.getTipo());

		// Adicionando informações ao painel
		infoPanel.add(idLabel);
		infoPanel.add(nameLabel);
		infoPanel.add(priceLabel);
		infoPanel.add(classIndLabel);
		infoPanel.add(genreLabel);
		infoPanel.add(stockLabel);
		infoPanel.add(typeLabel);

		// Botão de carrinho + a logica para comprar
		JButton cartButton = new JButton("Carrinho");
		mainPanel.add(cartButton, BorderLayout.SOUTH);

		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quantidadeStr = JOptionPane.showInputDialog(TelaInicialCliente.this,
						"Quantos itens deseja adicionar ao carrinho?");
				if (quantidadeStr == null || quantidadeStr.isEmpty()) {
					return;
				}

				int quantidade = Integer.parseInt(quantidadeStr);

				List<Integer> idsSelecionados = new ArrayList<>();

				for (int i = 0; i < quantidade; i++) {
					String id = JOptionPane.showInputDialog(TelaInicialCliente.this,
							"Digite o ID da " + (i + 1) + "ª mídia");
					if (id == null || id.isEmpty()) {
						// caso o cliente cancele a operação vai parar de pedir as IDs
						break;
					}
					try {
						int idMidia = Integer.parseInt(id);
						idsSelecionados.add(idMidia);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(TelaInicialCliente.this, "Por favor, digite um ID válido.",
								"Erro", JOptionPane.ERROR_MESSAGE);
						i--; // Para que o cliente possa tentar novamente
					}
				}

				if (idsSelecionados.isEmpty()) {
					// se nenhum item foi selecionado, então não há nada para comprar
					return;
				}

				midiaDAO.exibirInformacoesMidiasSelecionadas(idsSelecionados, mainPanel);

				int opcao = JOptionPane.showConfirmDialog(TelaInicialCliente.this, "Deseja comprar os itens?");
				if (opcao == JOptionPane.YES_OPTION) {
					Carrinho carrinho = new Carrinho();

					for (Integer id : idsSelecionados) {
						Midia midia = MidiaDAO.obterMidiaPorId(id);
						if (midia != null) {
							carrinho.adicionarItem(midia);
						}
					}

					int opcao2 = JOptionPane.showConfirmDialog(TelaInicialCliente.this,
							"Total da compra: R$ " + carrinho.getTotalCompra() + "\nDeseja finalizar a compra?");
					if (opcao2 == JOptionPane.YES_OPTION) {
						exibirOpcoesDePagamento(carrinho);
					}
				}
			}
		});

		// campo de pesquisa + lógica
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = searchField.getText();
				if (!nome.isEmpty()) {
					ListagemFrame<Midia> listagemFrame = new ListagemFrame<>("Resultado da Busca",
							new ListagemDAO<Midia>() {
								@Override
								public void listar(JPanel mainPanel) {
									midiaDAO.consultarMidia(nome, mainPanel);
								}
							});
					listagemFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(TelaInicialCliente.this, "Digite um nome para pesquisar.");
				}
			}
		});
	}

	private void exibirOpcoesDeListagem() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ButtonGroup group = new ButtonGroup();

		JRadioButton filmeRadioButton = new JRadioButton("Filme");
		panel.add(filmeRadioButton, BorderLayout.NORTH);
		group.add(filmeRadioButton);

		JRadioButton musicaRadioButton = new JRadioButton("Album");
		panel.add(musicaRadioButton, BorderLayout.CENTER);
		group.add(musicaRadioButton);

		JRadioButton jogoRadioButton = new JRadioButton("Jogo");
		panel.add(jogoRadioButton, BorderLayout.SOUTH);
		group.add(jogoRadioButton);

		JOptionPane.showMessageDialog(this, panel, "Escolha o tipo de mídia", JOptionPane.PLAIN_MESSAGE);

		if (filmeRadioButton.isSelected()) {
			FilmeDAO filmeDAO = new FilmeDAO();
			ListagemFrame<Filme> listagemFrame = new ListagemFrame<>("Listagem de Filmes", filmeDAO);
			listagemFrame.setVisible(true);
		} else if (musicaRadioButton.isSelected()) {
			AlbumDAO albumDAO = new AlbumDAO();
			ListagemFrame<Album> listagemFrame = new ListagemFrame<>("Listagem de Álbuns", albumDAO);
			listagemFrame.setVisible(true);
		} else if (jogoRadioButton.isSelected()) {
			JogoDAO jogoDAO = new JogoDAO();
			ListagemFrame<Jogo> listagemFrame = new ListagemFrame<>("Listagem de Jogos", jogoDAO);
			listagemFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Selecione uma opção!");
			return;
		}
	}

	private void exibirOpcoesDePerfil() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ButtonGroup group = new ButtonGroup();

		JRadioButton atualizarPerfilRadioButton = new JRadioButton("Atualizar Perfil");
		panel.add(atualizarPerfilRadioButton, BorderLayout.NORTH);
		group.add(atualizarPerfilRadioButton);

		JRadioButton apagarPerfilRadioButton = new JRadioButton("Apagar Perfil");
		panel.add(apagarPerfilRadioButton, BorderLayout.CENTER);
		group.add(apagarPerfilRadioButton);

		JOptionPane.showMessageDialog(this, panel, "Escolha uma opção:", JOptionPane.PLAIN_MESSAGE);

		if (atualizarPerfilRadioButton.isSelected()) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			JOptionPane.showMessageDialog(null, "Opção selecionada: Atualizar Perfil");
			usuarioDAO.alterarUsuario(this.usuario.getId());
			
		} else if (apagarPerfilRadioButton.isSelected()){
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			JOptionPane.showMessageDialog(null, "Opção selecionada: Apagar Perfil");
			int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o seu perfil?\n\nEssa ação não pode ser desfeita.");
			if (opcao == JOptionPane.YES_OPTION) {
			usuarioDAO.apagarUsuario(this.usuario.getId());
			JOptionPane.showMessageDialog(null, "Perfil apagado com sucesso");
			dispose();

		}
		
		
	} else {
		JOptionPane.showMessageDialog(null, "Selecione uma opção!");
		return;
	}
	
	}

	public void exibirOpcoesDePagamento(Carrinho carrinho) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));

		ButtonGroup group = new ButtonGroup();

		JRadioButton boletoRadioButton = new JRadioButton("Boleto");
		group.add(boletoRadioButton);
		panel.add(boletoRadioButton);

		JRadioButton cartaoRadioButton = new JRadioButton("Cartão");
		group.add(cartaoRadioButton);
		panel.add(cartaoRadioButton);

		JRadioButton pixRadioButton = new JRadioButton("Pix");
		group.add(pixRadioButton);
		panel.add(pixRadioButton);

		int result = JOptionPane.showConfirmDialog(null, panel, "Escolha a forma de pagamento:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			Pagamento pagamento = null;

			try {
				if (boletoRadioButton.isSelected()) {
					JTextField codigoBarrasField = new JTextField();
					JTextField dataVencimentoField = new JTextField();
					Object[] boletoFields = { "Código de Barras:", codigoBarrasField,
							"Data de Vencimento (dd/MM/yyyy):", dataVencimentoField };
					int boletoResult = JOptionPane.showConfirmDialog(null, boletoFields, "Informações do Boleto",
							JOptionPane.OK_CANCEL_OPTION);
					if (boletoResult == JOptionPane.OK_OPTION) {
						String codigoBarras = codigoBarrasField.getText();
						String dataVencimento = dataVencimentoField.getText();
						if (codigoBarras.isEmpty() || dataVencimento.isEmpty()) {
							throw new IllegalArgumentException("Todos os campos do boleto devem ser preenchidos.");
						}
						pagamento = new Boleto(0, codigoBarras, dataVencimento);
					}
				} else if (cartaoRadioButton.isSelected()) {
					JTextField nomeTitularField = new JTextField();
					JTextField numeroCartaoField = new JTextField();
					JTextField cvvField = new JTextField();
					JTextField validadeField = new JTextField();
					Object[] cartaoFields = { "Nome do Titular:", nomeTitularField, "Número do Cartão:",
							numeroCartaoField, "CVV:", cvvField, "Validade (MM/AA):", validadeField };
					int cartaoResult = JOptionPane.showConfirmDialog(null, cartaoFields, "Informações do Cartão",
							JOptionPane.OK_CANCEL_OPTION);
					if (cartaoResult == JOptionPane.OK_OPTION) {
						String nomeTitular = nomeTitularField.getText();
						String numeroCartao = numeroCartaoField.getText();
						String cvvStr = cvvField.getText();
						String validade = validadeField.getText();
						if (nomeTitular.isEmpty() || numeroCartao.isEmpty() || cvvStr.isEmpty() || validade.isEmpty()) {
							throw new IllegalArgumentException("Todos os campos do cartão devem ser preenchidos.");
						}
						int cvv = Integer.parseInt(cvvStr);
						pagamento = new Cartao(0, nomeTitular, numeroCartao, cvv, validade);
					}
				} else if (pixRadioButton.isSelected()) {
					JTextField chavePixField = new JTextField();
					Object[] pixFields = { "Chave Pix:", chavePixField };
					int pixResult = JOptionPane.showConfirmDialog(null, pixFields, "Informações do Pix",
							JOptionPane.OK_CANCEL_OPTION);
					if (pixResult == JOptionPane.OK_OPTION) {
						String chavePix = chavePixField.getText();
						if (chavePix.isEmpty()) {
							throw new IllegalArgumentException("A chave Pix deve ser preenchida.");
						}
						pagamento = new Pix(0, chavePix);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma opção!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (pagamento != null) {
					if (pagamento.validarInformacoes()) {
						pagamento.aplicarDesconto(carrinho);    
						JOptionPane.showMessageDialog(null,
								"Desconto aplicado. Total da compra: R$ " + String.format("%.2f",carrinho.getTotalCompra()), "Pagamento",
								JOptionPane.INFORMATION_MESSAGE);
						carrinho.comprar();
						JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
						dispose();
						TelaInicialCliente homeCliente = new TelaInicialCliente();
			            homeCliente.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null,
								"Informações de pagamento inválidas. Por favor, tente novamente.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	
}
