/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Funcionario;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author lucas
 */
public class FuncionarioDAOTest {
    
    public FuncionarioDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {
        
        Funcionario fun = new Funcionario(11, "João Pedro Silva", "111.111.111-11", "741852963");
        FuncionarioDAO dao = new FuncionarioDAO();
        
        if(dao.save(fun)){
            System.out.println("Salvo");
        }else{
            fail("Erro ao Salvar");
        }
    }
    
    @Test
    @Ignore
    public void atualizar() {
        
        Funcionario fun = new Funcionario();
        fun.setCodigo(11);
        fun.setNome("João Pedro da Silva");
        
        FuncionarioDAO dao = new FuncionarioDAO();
        
        if(dao.update(fun)){
            System.out.println("Atualizado");
        }else{
            fail("Erro ao Atualizar");
        }
    }
    
    @Test
    @Ignore
    public void deletar() {
        
        Funcionario fun = new Funcionario();
        fun.setCodigo(11);
        
        FuncionarioDAO dao = new FuncionarioDAO();
        
        if(dao.delete(fun)){
            System.out.println("Deletado");
        }else{
            fail("Erro ao Deletar");
        }
    }
    
    @Test
    @Ignore
    public void listar(){
        
        FuncionarioDAO dao = new FuncionarioDAO();
        
        for(Funcionario c: dao.findAll()){
        
            System.out.println("Codigo: "+c.getCodigo()+" Nome: "+c.getNome()+" CPF: "+c.getCpf()+" RG: "+c.getRg());
        }
        
    }
    
    @Test
    //@Ignore
    public void nextId(){
        FuncionarioDAO dao = new FuncionarioDAO();
        
        int id = dao.autoIncrement();
        System.out.println("Proximo codigo: "+id);
    }
    
}
