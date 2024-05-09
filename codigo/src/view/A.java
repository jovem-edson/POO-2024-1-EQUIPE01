package view;
import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class A extends JFrame {

    public A() {
        setTitle("Meu Aplicativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

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
        JButton btn1 = new JButton("Botão 1");
        JButton btn2 = new JButton("Botão 2");
        JButton btn3 = new JButton("Botão 3");
        toolBar.add(btn1);
        toolBar.add(btn2);
        toolBar.add(btn3);
        headerPanel.add(toolBar, BorderLayout.SOUTH);
        
        JPanel panel = new JPanel();
        headerPanel.add(panel, BorderLayout.CENTER);
        
        JLabel titulo = new JLabel("GALAXY");
        panel.add(titulo);
        
        JPanel footerPanel = new JPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        JLabel footerLabel = new JLabel("Rodapé");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerLabel);
        try {
            URL url = new URL("https://upload.wikimedia.org/wikipedia/en/9/9a/System_Of_A_Down-Hypnotize.jpg");
            ImageIcon icon = new ImageIcon(url);
            
            JPanel imagePanel = new JPanel();
            mainPanel.add(imagePanel, BorderLayout.EAST);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds(10, 60, 200, 200);
            mainPanel.add(imageLabel, BorderLayout.WEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            A frame = new A();
            frame.setVisible(true);
        });
    }
}
