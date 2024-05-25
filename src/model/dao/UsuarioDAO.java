package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.beans.Cliente;
import model.beans.Usuario;
import model.beans.Vendedor;

public class UsuarioDAO {
	PreparedStatement st;
	ResultSet rs;

	public int validarLogin(String email, String senha) {
		boolean usuarioEncontrado = false;
		;
		int idUsuario = -1;
		try (Connection conecta = ConectaBD.conexao()) {
			String sql = "SELECT * FROM usuario WHERE email like '" + email + "' AND senha like '" + senha + "'";

			try (PreparedStatement st = conecta.prepareStatement(sql)) {

				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						idUsuario = rs.getInt("idUsuario");
						usuarioEncontrado = true;
					}
					if (usuarioEncontrado == false) {
						JOptionPane.showMessageDialog(null, "Login Invalido.");
						System.out.println("Id do Usuario: " + idUsuario);

						return -1;
					}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar usuario: " + e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
		return idUsuario;
	}

	public Usuario buscarUsuario(int idUsuario) {
		Usuario usuario = new Usuario();
		try (Connection conecta = ConectaBD.conexao()) {
			String sql = "SELECT * FROM usuario WHERE idUsuario = ?";

			try (PreparedStatement st = conecta.prepareStatement(sql)) {
				st.setInt(1, idUsuario);
				try (ResultSet rs = st.executeQuery()) {
					while (rs.next()) {
						if (rs.getString("tipo").equals("Vendedor")) {
							usuario = new Vendedor();
							usuario.setId(rs.getInt("idUsuario"));
							usuario.setNome(rs.getString("nome"));
							usuario.setEmail(rs.getString("email"));
							usuario.setSenha(rs.getString("senha"));
							usuario.setCpf(rs.getString("cpf"));
						} else {
							usuario = new Cliente();
							usuario.setId(rs.getInt("idUsuario"));
							usuario.setNome(rs.getString("nome"));
							usuario.setEmail(rs.getString("email"));
							usuario.setSenha(rs.getString("senha"));
							usuario.setCpf(rs.getString("cpf"));
						}
					}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao buscar Usuario: " + e.getMessage());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}

		return usuario;
	}

	public boolean cadastrarUsuario(String nome, String CPF, String email, String senha, String tipo) {
		try (Connection conecta = ConectaBD.conexao()) {
			try {

				conecta.setAutoCommit(false); // desativando commit automático

				String sqlUsuario = "INSERT INTO usuario (nome, cpf, email, senha, tipo) VALUES (?, ?, ?, ?, ?)";

				st = conecta.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
				st.setString(1, nome);
				st.setString(2, CPF);
				st.setString(3, email);
				st.setString(4, senha);
				st.setString(5, tipo);
				st.executeUpdate();

				// obtendo ID gerado
				rs = st.getGeneratedKeys();
				int lastId = 0;
				if (rs.next()) {
					lastId = rs.getInt(1);
				}

				rs.close();
				st.close();

				if (tipo.equals("Vendedor")) {
					// Inserindo os dados na tabela vendedor a partir do ID obtido ali em cima

					String sqlVendedor = "INSERT INTO vendedor (idVendedor) VALUES (?)";
					st = conecta.prepareStatement(sqlVendedor);
					st.setInt(1, lastId);

					// Executando o comando INSERT
					st.executeUpdate();
					conecta.commit();

					JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
				} else {
					String sqlCliente = "INSERT INTO cliente (idCliente) VALUES (?)";
					st = conecta.prepareStatement(sqlCliente);
					st.setInt(1, lastId);

					// Executando o comando INSERT
					st.executeUpdate();
					conecta.commit();

					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
				}

				return true;

			} catch (SQLException e) {
				String erro = e.getMessage();
				if (erro.contains("Duplicate entry")) {
					JOptionPane.showMessageDialog(null, "Cliente já cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + erro);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
		}
		return false;
	}

	// para essas funções tive que implementar as transações novamente 
	public void alterarUsuario(int id) {
        String tipoUsuario = obterTipoUsuario(id);
        if (tipoUsuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            return;
        }

        String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome:");
        if (novoNome == null || novoNome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não pode ser vazio.");
            return;
        }

        String novoCpf = JOptionPane.showInputDialog(null, "Digite o novo CPF:");
        if (novoCpf == null || novoCpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF não pode ser vazio.");
            return;
        }

        String novoEmail = JOptionPane.showInputDialog(null, "Digite o novo email:");
        if (novoEmail == null || novoEmail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email não pode ser vazio.");
            return;
        }

        String novaSenha = JOptionPane.showInputDialog(null, "Digite a nova senha:");
        if (novaSenha == null || novaSenha.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Senha não pode ser vazia.");
            return;
        }

        // Atualizando n o bd
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, senha = ? WHERE idUsuario = ?";
        try (Connection conecta = ConectaBD.conexao();
             PreparedStatement st = conecta.prepareStatement(sql)) {
            st.setString(1, novoNome);
            st.setString(2, novoCpf);
            st.setString(3, novoEmail);
            st.setString(4, novaSenha);
            st.setInt(5, id);

            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void apagarUsuario(int id) {
        String tipoUsuario = obterTipoUsuario(id);
        if (tipoUsuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            return;
        }

        try (Connection conecta = ConectaBD.conexao()) {
            conecta.setAutoCommit(false);

            try {
                if (tipoUsuario.equals("Cliente")) {
                    String sqlCliente = "DELETE FROM cliente WHERE idCliente = ?";
                    try (PreparedStatement stCliente = conecta.prepareStatement(sqlCliente)) {
                        stCliente.setInt(1, id);
                        stCliente.executeUpdate();
                    }
                } else if (tipoUsuario.equals("Vendedor")) {
                    String sqlVendedor = "DELETE FROM vendedor WHERE idVendedor = ?";
                    try (PreparedStatement stVendedor = conecta.prepareStatement(sqlVendedor)) {
                        stVendedor.setInt(1, id);
                        stVendedor.executeUpdate();
                    }
                }

                String sqlUsuario = "DELETE FROM usuario WHERE idUsuario = ?";
                try (PreparedStatement stUsuario = conecta.prepareStatement(sqlUsuario)) {
                    stUsuario.setInt(1, id);
                    stUsuario.executeUpdate();
                }

                conecta.commit();
                JOptionPane.showMessageDialog(null, "Usuário apagado com sucesso!");

            } catch (SQLException e) {
                // isso desfaz a transação se der algum problema
                conecta.rollback();
                JOptionPane.showMessageDialog(null, "Erro ao apagar usuário: " + e.getMessage());
            } finally {
                // Ativa novamente o auto-commit
                conecta.setAutoCommit(true);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private String obterTipoUsuario(int id) {
        String sql = "SELECT tipo FROM usuario WHERE idUsuario = ?";
        try (Connection conecta = ConectaBD.conexao();
             PreparedStatement st = conecta.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipo");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter tipo de usuário: " + e.getMessage());
        }
        return null;
    }
	}

