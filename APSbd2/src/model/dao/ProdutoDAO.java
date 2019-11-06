/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Produto;



/**
 *
 * @author lucas
 */
public class ProdutoDAO {
    private Connection con = null;

    public ProdutoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Produto produto){
    
        String sql = "INSERT INTO APP.TB_PRODUTO (PRO_CODIGO, PRO_DESCRICAO, PRO_ESTOQUE) VALUES (?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getCodigo());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getEstoque());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public List<Produto> findAll(){
    
        String sql = "SELECT * FROM APP.TB_PRODUTO";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Produto produto = new Produto();
                
                produto.setCodigo(rs.getInt("PRO_CODIGO"));
                produto.setDescricao(rs.getString("PRO_DESCRICAO"));
                produto.setEstoque(rs.getInt("PRO_ESTOQUE"));
                
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
    
    
    public int autoIncrement(){
    
        String sql = "SELECT MAX(PRO_CODIGO) AS ID FROM APP.TB_PRODUTO";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;        
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next())
            {
                id = rs.getInt("ID");    
            }            
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        }       
        id++;
        return id;
    }
    
    public boolean update(Produto produto){
    
        String sql = "UPDATE APP.TB_PRODUTO SET PRO_DESCRICAO = ?, PRO_ESTOQUE = ? WHERE PRO_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(3, produto.getCodigo());
            stmt.setInt(2, produto.getEstoque());
            stmt.setString(1, produto.getDescricao());            
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }

    
    public boolean delete(Produto produto){
    
        String sql = "DELETE FROM APP.TB_PRODUTO WHERE PRO_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getCodigo());            
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
}
