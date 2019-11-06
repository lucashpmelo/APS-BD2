/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Produto;
import model.bean.ProdutoVenda;
import model.bean.Venda;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author lucas
 */
public class ProdutoVendaDAOTest {
    
    public ProdutoVendaDAOTest() {
    }

    @Test
    //@Ignore
    public void inserir() {

        Produto produto = new Produto();
        produto.setCodigo(2);

        Venda venda = new Venda();
        venda.setCodigo(2);

        ProdutoVenda proven = new ProdutoVenda();
        ProdutoVendaDAO dao = new ProdutoVendaDAO();

        proven.setCodigo(dao.autoIncrement());
        proven.setProduto(produto);
        proven.setVenda(venda);
        proven.setQuantidade(9);
        proven.setValor(27.00);

        if (dao.save(proven)) {
            System.out.println("Salvo");
        } else {
            fail("Erro ao Salvar");
        }
    }
    
    @Test
    @Ignore
    public void listar(){
        
        ProdutoVendaDAO dao = new ProdutoVendaDAO();
        
        for(ProdutoVenda c: dao.findAll()){
        
            System.out.println("Codigo Produto Venda: "+c.getCodigo()+" Codigo Venda: "+c.getVenda().getCodigo()+" Codigo Produto: "+c.getProduto().getCodigo());
        }
        
    }
}
