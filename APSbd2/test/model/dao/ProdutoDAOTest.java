/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Produto;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author lucas
 */
public class ProdutoDAOTest {
    
    public ProdutoDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {
        
        Produto pro = new Produto(11, "João Pedro Silva", 2);
        ProdutoDAO dao = new ProdutoDAO();
        
        if(dao.save(pro)){
            System.out.println("Salvo");
        }else{
            fail("Erro ao Salvar");
        }
    }
    
    @Test
    @Ignore
    public void atualizar() {
        
        Produto pro = new Produto();
        pro.setCodigo(11);
        pro.setDescricao("João Pedro da Silva");
        
        ProdutoDAO dao = new ProdutoDAO();
        
        if(dao.update(pro)){
            System.out.println("Atualizado");
        }else{
            fail("Erro ao Atualizar");
        }
    }
    
    @Test
    @Ignore
    public void deletar() {
        
        Produto pro = new Produto();
        pro.setCodigo(11);
        
        ProdutoDAO dao = new ProdutoDAO();
        
        if(dao.delete(pro)){
            System.out.println("Deletado");
        }else{
            fail("Erro ao Deletar");
        }
    }
    
    @Test
    //@Ignore
    public void listar(){
        
        ProdutoDAO dao = new ProdutoDAO();
        
        for(Produto c: dao.findAll()){
        
            System.out.println("Codigo: "+c.getCodigo()+" Nome: "+c.getDescricao()+" Quantidade: "+c.getEstoque());
        }
        
    }
    
    @Test
    @Ignore
    public void nextId(){
        ProdutoDAO dao = new ProdutoDAO();
        
        int id = dao.autoIncrement();
        System.out.println("Proximo codigo: "+id);
    }
    
}
