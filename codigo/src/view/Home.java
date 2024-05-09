package view;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class Home extends JFrame {

    private JRadioButton filmeRadioButton;
    private JRadioButton musicaRadioButton;
    private JRadioButton jogoRadioButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Home frame = new Home();
            frame.setVisible(true);
        });
    }

    public Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(492, 379);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 192));
        panel.setBounds(0, 0, 434, 42);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("GALAXY");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Garamond", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 14, 109, 14);
        panel.add(lblNewLabel);
        
        TextField textField = new TextField();
        textField.setText("Pesquisar...");
        textField.setBounds(293, 10, 120, 22);
        panel.add(textField);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBounds(0, 41, 434, 16);
        contentPane.add(toolBar);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirOpcoesDeCadastro();
            }
        });
        toolBar.add(cadastrarButton);

        JButton btnNewButton_1 = new JButton("Listar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        toolBar.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Atualizar");
        toolBar.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Excluir");
        toolBar.add(btnNewButton_3);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setBounds(286, 68, 46, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(286, 105, 46, 14);
        contentPane.add(lblNewLabel_2);
        
        try {
            URL url = new URL("https://upload.wikimedia.org/wikipedia/en/9/9a/System_Of_A_Down-Hypnotize.jpg");
            ImageIcon icon = new ImageIcon(url);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(10, 60, 200, 200);
            contentPane.add(imageLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exibirOpcoesDeCadastro() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ButtonGroup group = new ButtonGroup();

        filmeRadioButton = new JRadioButton("Filme");
        panel.add(filmeRadioButton, BorderLayout.NORTH);
        group.add(filmeRadioButton);

        musicaRadioButton = new JRadioButton("Música");
        panel.add(musicaRadioButton, BorderLayout.CENTER);
        group.add(musicaRadioButton);

        jogoRadioButton = new JRadioButton("Jogo");
        panel.add(jogoRadioButton, BorderLayout.SOUTH);
        group.add(jogoRadioButton);

        JOptionPane.showMessageDialog(this, panel, "Escolha o tipo de mídia", JOptionPane.PLAIN_MESSAGE);

        if (filmeRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Filme");
            // Aqui você pode chamar o método para cadastrar um filme
        } else if (musicaRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Música");
            // Aqui você pode chamar o método para cadastrar uma música
        } else if (jogoRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Opção selecionada: Jogo");
            // Aqui você pode chamar o método para cadastrar um jogo
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma opção!");
        }
    }
}
