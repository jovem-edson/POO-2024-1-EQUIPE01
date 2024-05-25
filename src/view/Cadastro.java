package view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.dao.UsuarioDAO;


public class Cadastro extends JFrame {
	private JTextField nomeInput;
	private JTextField cpfInput;
	private JTextField emailInput;
	private JPasswordField senhaInput;
	private JComboBox tipoInput;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
    String[] tiposUsuario = {"Cliente", "Vendedor" };

	
	public Cadastro() {
        setSize(1600, 900);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(128, 128, 192));
        panel_1.setBounds(784, 0, 800, 861);
        panel.add(panel_1);
        
        JButton btnEntrar = new JButton("CADASTRAR");
        btnEntrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String tipo = tiposUsuario[tipoInput.getSelectedIndex()];
                
        		char[] passwordChars = senhaInput.getPassword(); // jPasswordField é a referência para o seu JPasswordField
				String senha = new String(passwordChars); //
				
				if(usuarioDAO.cadastrarUsuario(nomeInput.getText(), cpfInput.getText(), emailInput.getText(), senha, tipo)) {
					Login login = new Login();
					dispose();
			        login.setVisible(true);
				};
        	}
        });
        btnEntrar.setForeground(new Color(128, 128, 192));
        btnEntrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        btnEntrar.setBackground(Color.WHITE);
        btnEntrar.setBounds(59, 700, 131, 30);
        panel_1.add(btnEntrar);
        
        JLabel lblEmail_1 = new JLabel("Nome:");
        lblEmail_1.setBounds(50, 150, 415, 63);
        panel_1.add(lblEmail_1);
        lblEmail_1.setForeground(Color.WHITE);
        lblEmail_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblEmail_1.setBackground(Color.WHITE);
        lblEmail_1.setAlignmentX(50.0f);
        
        nomeInput = new JTextField();
        nomeInput.setBounds(50, 200, 263, 30);
        panel_1.add(nomeInput);
        nomeInput.setHorizontalAlignment(SwingConstants.LEFT);
        nomeInput.setFont(new Font("Arial", Font.PLAIN, 11));
        nomeInput.setColumns(10);
        
        JLabel lblEmail_1_1 = new JLabel("CPF:");
        lblEmail_1_1.setBounds(50, 250, 415, 63);
        panel_1.add(lblEmail_1_1);
        lblEmail_1_1.setForeground(Color.WHITE);
        lblEmail_1_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblEmail_1_1.setBackground(Color.WHITE);
        lblEmail_1_1.setAlignmentX(50.0f);
        
        cpfInput = new JTextField();
        cpfInput.setBounds(50, 300, 263, 30);
        panel_1.add(cpfInput);
        cpfInput.setHorizontalAlignment(SwingConstants.LEFT);
        cpfInput.setFont(new Font("Arial", Font.PLAIN, 11));
        cpfInput.setColumns(10);
        
        JLabel lblEmail_1_1_1 = new JLabel("E-mail:");
        lblEmail_1_1_1.setBounds(50, 350, 415, 63);
        panel_1.add(lblEmail_1_1_1);
        lblEmail_1_1_1.setForeground(Color.WHITE);
        lblEmail_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblEmail_1_1_1.setBackground(Color.WHITE);
        lblEmail_1_1_1.setAlignmentX(50.0f);
        
        emailInput = new JTextField();
        emailInput.setBounds(50, 400, 263, 30);
        panel_1.add(emailInput);
        emailInput.setHorizontalAlignment(SwingConstants.LEFT);
        emailInput.setFont(new Font("Arial", Font.PLAIN, 11));
        emailInput.setColumns(10);
        
        JLabel lblEmail_1_1_1_1 = new JLabel("Senha:");
        lblEmail_1_1_1_1.setBounds(50, 450, 415, 63);
        panel_1.add(lblEmail_1_1_1_1);
        lblEmail_1_1_1_1.setForeground(Color.WHITE);
        lblEmail_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblEmail_1_1_1_1.setBackground(Color.WHITE);
        lblEmail_1_1_1_1.setAlignmentX(50.0f);
        
        senhaInput = new JPasswordField();
        senhaInput.setBounds(50, 500, 263, 30);
        panel_1.add(senhaInput);
        
        JLabel lblEmail_1_1_1_1_2 = new JLabel("Tipo de Usuário:");
        lblEmail_1_1_1_1_2.setBounds(50, 550, 415, 63);
        panel_1.add(lblEmail_1_1_1_1_2);
        lblEmail_1_1_1_1_2.setForeground(Color.WHITE);
        lblEmail_1_1_1_1_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblEmail_1_1_1_1_2.setBackground(Color.WHITE);
        lblEmail_1_1_1_1_2.setAlignmentX(50.0f);
                
        tipoInput = new JComboBox(tiposUsuario);
        tipoInput.setSelectedIndex(0);
        tipoInput.setBounds(50, 600, 263, 30);
        panel_1.add(tipoInput);
        
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Login login = new Login();
				dispose();
		        login.setVisible(true);
        		
        	}
        });
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        btnVoltar.setBackground(new Color(128, 128, 192));
        btnVoltar.setBounds(59, 740, 131, 30);
        panel_1.add(btnVoltar);
        
        
        
        JLabel nomeLocadora_1 = new JLabel("    Cadastro de Usuário");
        nomeLocadora_1.setForeground(new Color(128, 128, 192));
        nomeLocadora_1.setFont(new Font("Segoe UI", Font.BOLD, 47));
        nomeLocadora_1.setBackground(Color.WHITE);
        nomeLocadora_1.setAlignmentX(50.0f);
        nomeLocadora_1.setBounds(0, 450, 551, 63);
        panel.add(nomeLocadora_1);

	}
	
}
