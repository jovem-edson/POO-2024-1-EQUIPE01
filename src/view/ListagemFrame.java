package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;

import model.dao.*;
import model.beans.*;

public class ListagemFrame<T> extends JFrame {
    protected JPanel mainPanel;

    public ListagemFrame(String title, ListagemDAO<T> listagemDAO) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(mainPanel);

        exibirListagem(listagemDAO);
    }

    protected void exibirListagem(ListagemDAO<T> listagemDAO) {
        try {
            listagemDAO.listar(mainPanel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        }
    }
}

    
    

	