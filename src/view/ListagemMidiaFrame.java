package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import model.beans.Midia;

public class ListagemMidiaFrame extends JFrame {
    
    public ListagemMidiaFrame(List<Midia> midias) {
        setTitle("Listagem de Mídias");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        
        
        
        for (Midia midia : midias) {
            JPanel midiaPanel = new JPanel();
            midiaPanel.setLayout(new BorderLayout());
            try {
                URL url = new URL(midia.getCapa());
                ImageIcon icon = new ImageIcon(url);
                JLabel imageLabel = new JLabel(icon);
                midiaPanel.add(imageLabel, BorderLayout.WEST);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(0, 1));
            infoPanel.add(new JLabel("ID: " + midia.getIdMidia()));
            infoPanel.add(new JLabel("Nome: " + midia.getNome()));
            infoPanel.add(new JLabel("Preço: " + String.format("%.2f", midia.getPreco()) + "R$"));
            infoPanel.add(new JLabel("Classificação Indicativa: " + midia.getClassIndicativa()));
            infoPanel.add(new JLabel("Gênero: " + midia.getGenero()));
            infoPanel.add(new JLabel("Estoque: " + midia.getQtdEstoque()));
            
            midiaPanel.add(infoPanel, BorderLayout.CENTER);
            mainPanel.add(midiaPanel);
        }
    }
}
