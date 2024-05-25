package model.beans;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.dao.MidiaDAO;

public class Carrinho {
	 private List<Midia> itens;
	 private Double totalCompra;

	    public Carrinho() {
	        this.itens = new ArrayList<>();
	        this.totalCompra = 0.0;
	    }

	    public void adicionarItem(Midia item) {
	        itens.add(item);
	        totalCompra += item.getPreco();
	    }

	    public void removerItem(Midia item) {
	        itens.remove(item);
	    }

	    public List<Midia> getItens() {
	        return itens;
	    }
	    
	    public void comprar() {
	        if (itens.isEmpty()) {
	            System.out.println("Carrinho vazio. Nada para comprar.");
	            return;
	        }

	        // aqui recupera as mídias com os IDs selecionados
	        MidiaDAO midiaDAO = new MidiaDAO();
	        for (Midia item : itens) {
	            Midia midia = midiaDAO.obterMidiaPorId(item.getIdMidia());
	            if (midia == null) {
	                JOptionPane.showMessageDialog(null, "Erro: Mídia com ID " + item.getIdMidia() + " não encontrada.");
	                continue;
	            }
	            // aqui é a aquela lógica de remover do BD
	            // verifica se a quantidade em estoque é suficiente
	            if (midia.getQtdEstoque() >= 1) {
	                // se for, diminui 1 do estoque
	                midia.setQtdEstoque(midia.getQtdEstoque() - 1);
	                MidiaDAO.atualizarEstoque(midia);
	            } else {
	                // Se não, remove a mídia do carrinho e do banco de dados
	                itens.remove(item);
	                MidiaDAO.atualizarEstoque(midia);
	                JOptionPane.showMessageDialog(null, "Erro: Mídia com ID " + item.getIdMidia() + " fora de estoque. Removida do carrinho e do banco de dados.");
	            }
	        }
	    }

	    public double getTotalCompra() {
	        return totalCompra;
	    }
	    
	    public void aplicarDesconto(double desconto) {
	        this.totalCompra -= desconto;
	        if (totalCompra < 0) {
	            totalCompra = 0.0;
	        }
	    }
	    public int tamanho() {
	        return itens.size();
	    }
	}
