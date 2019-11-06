/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Cliente;
import model.bean.Funcionario;
import model.bean.Venda;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author lucas
 */
public class VendaDAOTest {

    public VendaDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {

        Cliente cliente = new Cliente();
        cliente.setCodigo(3);

        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo(5);

        Venda ven = new Venda();
        VendaDAO dao = new VendaDAO();

        ven.setCodigo(dao.autoIncrement());
        ven.setCliente(cliente);
        ven.setFuncionario(funcionario);
        ven.setData("15-11-2017");

        if (dao.save(ven)) {
            System.out.println("Salvo");
        } else {
            fail("Erro ao Salvar");
        }
    }
    
    @Test
    //@Ignore
    public void listar(){
        
        VendaDAO dao = new VendaDAO();
        
        for(Venda c: dao.findAll()){
        
            System.out.println("Codigo Venda: "+c.getCodigo()+" Nome Cliente: "+c.getCliente().getNome()+" Nome Funcionario: "+c.getFuncionario().getNome()+" Data: "+c.getData());
        }
        
    }

}
