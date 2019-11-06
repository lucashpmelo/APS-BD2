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
public class Produto {
    private int codigo;
    private String descricao;
    private int estoque;

    public Produto() {
    }

    public Produto(int codigo, String descricao, int estoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.estoque = estoque;
    }   
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    
}
