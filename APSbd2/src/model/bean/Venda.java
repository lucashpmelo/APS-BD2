/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author lucas
 */
public class Venda {
    private int codigo;
    private Cliente cliente;
    private Funcionario funcionario;
    private String data;

    public Venda() {
    }

    public Venda(int codigo, Cliente cliente, Funcionario funcionario, String data) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.data = data;
    }   
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
