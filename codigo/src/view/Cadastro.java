package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
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
	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 192));
		panel.setBounds(195, 0, 239, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crie sua Conta");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 16));
		lblNewLabel.setBounds(70, 35, 111, 13);
		panel.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Garamond", Font.BOLD, 12));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(70, 93, 59, 13);
		panel.add(lblEmail);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 11));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(70, 106, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Garamond", Font.BOLD, 12));
		lblSenha.setBackground(Color.WHITE);
		lblSenha.setBounds(70, 149, 59, 13);
		panel.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 11));
		passwordField.setBounds(70, 162, 86, 20);
		panel.add(passwordField);
	}
}
