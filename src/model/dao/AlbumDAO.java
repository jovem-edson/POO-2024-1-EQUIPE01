package model.dao;

import model.beans.Album;

import java.awt.BorderLayout;
import java.net.URL;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Arrays;

public class AlbumDAO implements ListagemDAO<Album> {

	PreparedStatement st;
	ResultSet rs;

	public void cadastrarAlbum() {
		try (Connection conecta = ConectaBD.conexao()) {
			Album album = new Album();

			album.setNome(JOptionPane.showInputDialog("Digite o nome do álbum: "));
			album.setPreco(Float.parseFloat(JOptionPane.showInputDialog("Digite o preço do álbum: ")));
			album.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do álbum: "));
			album.setGenero(JOptionPane.showInputDialog("Digite o gênero do álbum: "));
			album.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do álbum: ")));
			album.setCapa(JOptionPane.showInputDialog("Informe a URL da capa do álbum: "));
			album.setAnoLancamento(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de lançamento do álbum: ")));
			

			int qtdArtistas = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de artistas ou bandas no album: "));
			
			for(int i = 0; i < qtdArtistas; i++) {
				album.adicionarArtista(JOptionPane.showInputDialog("Informe o nome do artista: "));
			}
			
			String artistaString = String.join(",", album.getArtistas());
			
			
			int qtdMusicas = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de musicas no álbum: "));

			for (int i = 1; i <= qtdMusicas; i++) {
				album.adicionarMusica(JOptionPane.showInputDialog("Digite o título da música " + i + " do album: "));
			}
			
			String musicaString = String.join(",", album.getMusicas()); 

			try {
				
				conecta.setAutoCommit(false); //desativando commit automático

				String sqlMidia = "INSERT INTO midia (nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo) VALUES (?,?,?,?,?,?,?)";
				st = conecta.prepareStatement(sqlMidia, PreparedStatement.RETURN_GENERATED_KEYS);
				st.setString(1, album.getNome());
				st.setDouble(2, album.getPreco());
				st.setString(3, album.getClassIndicativa());
				st.setString(4, album.getGenero());
				st.setInt(5, album.getQtdEstoque());
				st.setString(6, album.getCapa());
				st.setString(7, "album");
				st.executeUpdate();
				
				//obtendo ID gerado
				rs = st.getGeneratedKeys();
				int lastId = 0;
				if (rs.next()) {
					lastId = rs.getInt(1);
				}
				
				rs.close();
				st.close();
				
				//inserindo os dados na tabela album a partir do ID obtido ali ó
				
				String sqlAlbum = "INSERT INTO album (idAlbum, anoLancamento, artista, musica) VALUES (?,?,?,?)";
				st = conecta.prepareStatement(sqlAlbum);
				st.setInt(1, lastId);
				st.setInt(2, album.getAnoLancamento());
				st.setString(3, artistaString);
				st.setString(4, musicaString);
				
				// Executando o comando INSERT
				st.executeUpdate();
				conecta.commit();
				
				JOptionPane.showMessageDialog(null, "Álbum cadastrado com sucesso!");
			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Álbum já cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar álbum: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}

	@Override
	public void listar(JPanel mainPanel) {
		try (Connection conecta = ConectaBD.conexao()) {
			String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, a.artista, a.anoLancamento, a.musica, m.tipo\r\n"
					+ "FROM midia m\r\n"
					+ "INNER JOIN album a ON m.idMidia = a.idAlbum;";
            
            try (PreparedStatement st = conecta.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                
                while (rs.next()) {
                    JPanel midiaPanel = new JPanel();
                    midiaPanel.setLayout(new BorderLayout());
                    midiaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                    midiaPanel.add(infoPanel, BorderLayout.CENTER);

                    String capaUrl = rs.getString("capa");
                    try {
                        URL url = new URL(capaUrl);
                        ImageIcon icon = new ImageIcon(url);
                        JLabel imageLabel = new JLabel(icon);
                        midiaPanel.add(imageLabel, BorderLayout.WEST);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    infoPanel.add(new JLabel("ID: " + rs.getInt("idMidia")));
                    infoPanel.add(new JLabel("Nome: " + rs.getString("nome")));
                    infoPanel.add(new JLabel("Preço: " + String.format("%.2f",rs.getDouble("preco"))));
                    infoPanel.add(new JLabel("Classificação Indicativa: " + rs.getString("classIndicativa")));
                    infoPanel.add(new JLabel("Gênero: " + rs.getString("genero")));
                    infoPanel.add(new JLabel("Quantidade em Estoque: " + rs.getInt("qtdEstoque")));
                    infoPanel.add(new JLabel("Ano de lançamento: " + rs.getInt("anoLancamento")));
                    infoPanel.add(new JLabel("Artistas: " + rs.getString("artista")));
                    infoPanel.add(new JLabel("Músicas: " + rs.getString("musica")));
                    infoPanel.add(new JLabel("Tipo: " + rs.getString("tipo")));

                    mainPanel.add(midiaPanel);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar albuns: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
	
	public void consultarAlbum(String nome) {
		String dadosAlbums = "";
		try (Connection conecta = ConectaBD.conexao()) {
			String sql = "SELECT m.idMidia, m.nome, m.preco, m.classIndicativa, m.genero, m.qtdEstoque, m.capa, a.anoLancamento a.artista, a.musica, m.tipo\r\n"
					+ "FROM midia m\r\n"
					+ "INNER JOIN album a ON m.idMidia = a.idAlbum;"
					+ "WHERE m.nome LIKE ?"
					+ "ORDER BY m.idMidia";
			
			try (PreparedStatement st = conecta.prepareStatement(sql)) {
                st.setString(1, "%" + nome + "%");
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        dadosAlbums += "ID: " + rs.getInt("idMidia") + "\n" 
                                     + "Nome: " + rs.getString("nome") + "\n"
                                     + "Preço: " + String.format("%.2f",rs.getDouble("preco")) + "\n"
                                     + "Classificação Indicativa: " + rs.getString("classIndicativa") + "\n"
                                     + "Gênero: " + rs.getString("genero") + "\n"
                                     + "Quantidade em Estoque: " + rs.getInt("qtdEstoque") + "\n"
                                     + "Capa: " + rs.getString("capa") + "\n" 
                                     + "Ano de Lançamento: " + rs.getInt("anoLancamento") + "\n"
                                     + "Artistas: " + rs.getString("artista") + "\n"
                                     + "Músicas: " + rs.getString("musica") + "\n\n";
				}
				JOptionPane.showMessageDialog(null, dadosAlbums);
                }
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar álbum: " + e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
	}

	public void alterarAlbum(int idAlbum) {
		try (Connection conecta = ConectaBD.conexao()) {
			Album album = new Album();
			album.setNome(JOptionPane.showInputDialog("Digite o nome do álbum: "));
			album.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do álbum: ")));
			album.setClassIndicativa(JOptionPane.showInputDialog("Digite a classificação indicativa do álbum: "));
			album.setGenero(JOptionPane.showInputDialog("Digite o gênero do álbum: "));
			album.setQtdEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque do álbum: ")));
			album.setCapa(JOptionPane.showInputDialog("Digite o caminho da capa do álbum: "));
			album.setAnoLancamento(Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de lançamento do álbum: ")));
			
			int qtdArtistas = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de artistas ou bandas no album: "));
			
			for(int i = 0; i < qtdArtistas; i++) {
				album.adicionarArtista(JOptionPane.showInputDialog("Informe o nome do artista: "));
			}
			
			String artistaString = String.join(",", album.getArtistas());
			
			
			int qtdMusicas = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de musicas no álbum: "));

			for (int i = 1; i <= qtdMusicas; i++) {
				album.adicionarMusica("Digite o título da música " + i + " do album: ");
			}
			
			String musicaString = String.join(",", album.getMusicas());
			
			
			 try {
	                // Atualizar a tabela midia
	                st = conecta.prepareStatement(
	                        "UPDATE midia SET nome = ?, preco = ?, classIndicativa = ?, genero = ?, qtdEstoque = ?, capa = ? WHERE idMidia = ?");
	                st.setString(1, album.getNome());
	                st.setDouble(2, album.getPreco());
	                st.setString(3, album.getClassIndicativa());
	                st.setString(4, album.getGenero());
	                st.setInt(5, album.getQtdEstoque());
	                st.setString(6, album.getCapa());
	                st.setInt(7, idAlbum);
	                st.executeUpdate();
	                st.close();

	                // Atualizar a tabela album
	                st = conecta.prepareStatement(
	                        "UPDATE album SET anoLancamento = ?, artista = ?, musica = ? WHERE idAlbum = ?");
	                st.setInt(1, album.getAnoLancamento());
	                st.setString(2, artistaString);
	                st.setString(3, musicaString);
	                st.setInt(4, idAlbum);
	                st.executeUpdate();
	                st.close();

	                JOptionPane.showMessageDialog(null, "Álbum atualizado com sucesso!");
	            } catch (SQLException e) {
	                String erro = e.getMessage();
	                if (erro.contains("Duplicate entry")) {
	                    JOptionPane.showMessageDialog(null, "Álbum já cadastrado!");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Erro ao atualizar álbum: " + erro);
	                }
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
	        }
	    }

	public void deletarAlbum(int idAlbum) {
		try (Connection conecta = ConectaBD.conexao()) {
			try {
                // Primeiro, devemos deletar da tabela album
                st = conecta.prepareStatement("DELETE FROM album WHERE idAlbum=?");
                st.setInt(1, idAlbum);
                int resultadoAlbum = st.executeUpdate();
                st.close();

                if (resultadoAlbum == 0) {
                    JOptionPane.showMessageDialog(null, "Album não encontrado");
                    return; // Se o Album não for encontrado, para a execução
                }

                // Depois, deletar da tabela midia
                st = conecta.prepareStatement("DELETE FROM midia WHERE idMidia=?");
                st.setInt(1, idAlbum);
                int resultadoMidia = st.executeUpdate();
                st.close();

                // Verifica se a mídia foi removida
                if (resultadoMidia == 0)
                    JOptionPane.showMessageDialog(null, "Mídia não encontrada");
                else
                    JOptionPane.showMessageDialog(null, "O album e a mídia de registro " + idAlbum + " foram removidos com sucesso");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar filme: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
	
	public Album obterUltimoAlbumAdicionado() {
	    Album ultimoAlbum = null;

	    try (Connection conecta = ConectaBD.conexao()) {
	    	try {
	    		st = conecta.prepareStatement("SELECT * FROM album ORDER BY idAlbum DESC LIMIT 1");
	    		ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	        	int idMidia = rs.getInt("idMidia");
	        	int id = rs.getInt("idAlbum");
	            String nome = rs.getString("nome");
	            double preco = rs.getFloat("preco");
	            String classIndicativa = rs.getString("classIndicativa");
	            String genero = rs.getString("genero");
	            int qtdEstoque = rs.getInt("qtdEstoque");
	            String capa = rs.getString("capa");
	            String tipo = rs.getString("tipo");
	            int anoLancamento = rs.getInt("anoLancamento");
	            String artistaBD = rs.getString("artista");
	            String musicaBD = rs.getString("musica");
	            
	            
	            ArrayList<String> artistas = new ArrayList<>();
	            if (artistaBD != null && !artistaBD.isEmpty()) {
	                String[] artista = artistaBD.split(",");
	                artistas.addAll(Arrays.asList(artista));
	            }
	            
	            ArrayList<String> musicas = new ArrayList<>();
	            if (musicaBD != null && !musicaBD.isEmpty()) {
	                String[] musica = musicaBD.split(",");
	                musicas.addAll(Arrays.asList(musica));
	            }
	            
	            //id, nome, preco, classIndicativa, genero, qtdEstoque, capa, duracao, elenco
	            ultimoAlbum = new Album(idMidia, nome, preco, classIndicativa, genero, qtdEstoque, capa, tipo, id, anoLancamento, artistas, musicas);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());

	}
	    return ultimoAlbum;
	}
}
