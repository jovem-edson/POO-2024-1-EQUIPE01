package model.dao;

import javax.swing.JPanel;
import java.sql.SQLException;

public interface ListagemDAO<T> {
    void listar(JPanel mainPanel) throws SQLException;
}
