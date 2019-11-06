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
import model.bean.Funcionario;


/**
 *
 * @author lucas
 */
public class FuncionarioDAO {
    private Connection con = null;

    public FuncionarioDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Funcionario funcionario){
    
        String sql = "INSERT INTO APP.TB_FUNCIONARIO (FUN_CODIGO, FUN_NOME, FUN_CPF, FUN_RG) VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodigo());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getRg());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public List<Funcionario> findAll(){
    
        String sql = "SELECT * FROM APP.TB_FUNCIONARIO";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Funcionario funcionario = new Funcionario();
                
                funcionario.setCodigo(rs.getInt("FUN_CODIGO"));
                funcionario.setNome(rs.getString("FUN_NOME"));
                funcionario.setCpf(rs.getString("FUN_CPF"));
                funcionario.setRg(rs.getString("FUN_RG"));
                
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return funcionarios;
    }
    
    
    public int autoIncrement(){
    
        String sql = "SELECT MAX(FUN_CODIGO) AS ID FROM APP.TB_FUNCIONARIO";
        
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
    
    public boolean update(Funcionario funcionario){
    
        String sql = "UPDATE APP.TB_FUNCIONARIO SET FUN_NOME = ?, FUN_CPF = ?, FUN_RG = ? WHERE FUN_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);             
            stmt.setInt(4, funcionario.getCodigo());
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getRg());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }

    
    public boolean delete(Funcionario funcionario){
    
        String sql = "DELETE FROM APP.TB_FUNCIONARIO WHERE FUN_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodigo());            
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
