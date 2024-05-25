package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.dao.UsuarioDAO;
import model.beans.Usuario;
import model.beans.Vendedor;


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailInput;
	private JPasswordField senhaInput;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
        setSize(1600, 900);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(784, 0, 800, 861);
		panel.setBackground(new Color(128, 128, 192));
		contentPane.add(panel);
		panel.setLayout(null);
		
		emailInput = new JTextField();
		emailInput.setFont(new Font("Arial", Font.PLAIN, 11));
		emailInput.setHorizontalAlignment(SwingConstants.LEFT);
		emailInput.setBounds(73, 397, 263, 30);
		panel.add(emailInput);
		emailInput.setColumns(10);
		
		JLabel lblEmail_1 = new JLabel("E-mail:");
		lblEmail_1.setForeground(Color.WHITE);
		lblEmail_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblEmail_1.setBackground(Color.WHITE);
		lblEmail_1.setAlignmentX(50.0f);
		lblEmail_1.setBounds(70, 343, 415, 63);
		panel.add(lblEmail_1);
		
		JLabel lblEmail_1_1 = new JLabel("Senha:");
		lblEmail_1_1.setForeground(Color.WHITE);
		lblEmail_1_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblEmail_1_1.setBackground(Color.WHITE);
		lblEmail_1_1.setAlignmentX(50.0f);
		lblEmail_1_1.setBounds(70, 434, 415, 63);
		panel.add(lblEmail_1_1);
		
		senhaInput = new JPasswordField();
		senhaInput.setBounds(70, 488, 263, 30);
		panel.add(senhaInput);
		
		JButton btnEntrar = new JButton("ENTRAR");
		
		//DEVOLVE PRA MAIN UM USUÁRIO
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwordChars = senhaInput.getPassword(); // jPasswordField é a referência para o seu JPasswordField
				String senha = new String(passwordChars); // Convertendo os caracteres para uma string

				int idUsuario = usuarioDAO.validarLogin(emailInput.getText(), senha);
				System.out.println(idUsuario);				
				if (idUsuario != -1) {
					usuario = usuarioDAO.buscarUsuario(idUsuario);
					
					 if (usuario instanceof Vendedor) {
							dispose();
				        	Home home = new Home();
				            home.setVisible(true);
				        } else {
							dispose();
				        	TelaInicialCliente homeCliente = new TelaInicialCliente();
				        	homeCliente.setUsuario(usuario);
				            homeCliente.setVisible(true);
				        }
					
					
				}
			}
		});
		btnEntrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setForeground(new Color(128, 128, 192));
		btnEntrar.setBounds(74, 564, 131, 30);
		panel.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cadastro = new Cadastro();
				dispose();
		        cadastro.setVisible(true);
			}
		});
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnCadastrar.setBackground(new Color(128, 128, 192));
		btnCadastrar.setBounds(74, 604, 131, 30);
		panel.add(btnCadastrar);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(0, 0, 800, 861);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel nomeLocadora_1 = new JLabel("    Multi-Locadora Galaxy");
		nomeLocadora_1.setForeground(new Color(128, 128, 192));
		nomeLocadora_1.setFont(new Font("Segoe UI", Font.BOLD, 47));
		nomeLocadora_1.setBackground(Color.WHITE);
		nomeLocadora_1.setAlignmentX(50.0f);
		panel_1.add(nomeLocadora_1);
	}

	public Usuario getUsuario() {
		// TODO Auto-generated method stub
		return this.usuario;
	}
}
