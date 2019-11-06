/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cliente;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author lucas
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {
        
        Cliente cli = new Cliente(11, "João Pedro Silva", "111.111.111-11", "741852963");
        ClienteDAO dao = new ClienteDAO();
        
        if(dao.save(cli)){
            System.out.println("Salvo");
        }else{
            fail("Erro ao Salvar");
        }
    }
    
    @Test
    @Ignore
    public void atualizar() {
        
        Cliente cli = new Cliente();
        cli.setCodigo(11);
        cli.setNome("João Pedro da Silva");
        
        ClienteDAO dao = new ClienteDAO();
        
        if(dao.update(cli)){
            System.out.println("Atualizado");
        }else{
            fail("Erro ao Atualizar");
        }
    }
    
    @Test
    @Ignore
    public void deletar() {
        
        Cliente cli = new Cliente();
        cli.setCodigo(11);
        
        ClienteDAO dao = new ClienteDAO();
        
        if(dao.delete(cli)){
            System.out.println("Deletado");
        }else{
            fail("Erro ao Deletar");
        }
    }
    
    @Test
    @Ignore
    public void listar(){
        
        ClienteDAO dao = new ClienteDAO();
        
        for(Cliente c: dao.findAll()){
        
            System.out.println("Codigo: "+c.getCodigo()+" Nome: "+c.getNome()+" CPF: "+c.getCpf()+" RG: "+c.getRg());
        }
        
    }
    
    @Test
    //@Ignore
    public void nextId(){
        ClienteDAO dao = new ClienteDAO();
        
        int id = dao.autoIncrement();
        System.out.println("Proximo codigo: "+id);
    }
    
}
