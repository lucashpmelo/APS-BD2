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
import model.bean.Cliente;

/**
 *
 * @author lucas
 */
public class ClienteDAO {
   
    private Connection con = null;

    public ClienteDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Cliente cliente){
    
        String sql = "INSERT INTO APP.TB_CLIENTE (CLI_CODIGO, CLI_NOME, CLI_CPF, CLI_RG) VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getCodigo());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public List<Cliente> findAll(){
    
        String sql = "SELECT * FROM APP.TB_CLIENTE";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Cliente cliente = new Cliente();
                
                cliente.setCodigo(rs.getInt("CLI_CODIGO"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setCpf(rs.getString("CLI_CPF"));
                cliente.setRg(rs.getString("CLI_RG"));
                
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    
    
    public int autoIncrement(){
    
        String sql = "SELECT MAX(CLI_CODIGO) AS ID FROM APP.TB_CLIENTE";
        
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
    
    public boolean update(Cliente cliente){
    
        String sql = "UPDATE APP.TB_CLIENTE SET CLI_NOME = ?, CLI_CPF = ?, CLI_RG = ? WHERE CLI_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(4, cliente.getCodigo());
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }

    
    public boolean delete(Cliente cliente){
    
        String sql = "DELETE FROM APP.TB_CLIENTE WHERE CLI_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getCodigo());            
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
