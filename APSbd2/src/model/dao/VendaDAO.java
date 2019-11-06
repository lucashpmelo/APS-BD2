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
import model.bean.Funcionario;
import model.bean.Venda;

/**
 *
 * @author lucas
 */
public class VendaDAO {

    private Connection con = null;

    public VendaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(Venda venda) {

        String sql = "INSERT INTO APP.TB_VENDA (VEN_CODIGO, VEN_CODCLIENTE, VEN_CODFUNCIONARIO, VEN_DATA) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getCodigo());
            stmt.setInt(2, venda.getCliente().getCodigo());
            stmt.setInt(3, venda.getFuncionario().getCodigo());
            stmt.setString(4, venda.getData());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Venda> findAll() {

        String sql = "SELECT * FROM APP.TB_VENDA, APP.TB_CLIENTE, APP.TB_FUNCIONARIO WHERE VEN_CODCLIENTE = CLI_CODIGO AND VEN_CODFUNCIONARIO = FUN_CODIGO ORDER BY VEN_CODIGO";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setCodigo(rs.getInt("VEN_CODIGO"));
                venda.setData(rs.getString("VEN_DATA"));

                Cliente cliente = new Cliente();

                cliente.setCodigo(rs.getInt("CLI_CODIGO"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setCpf(rs.getString("CLI_CPF"));
                cliente.setRg(rs.getString("CLI_RG"));

                venda.setCliente(cliente);

                Funcionario funcionario = new Funcionario();

                funcionario.setCodigo(rs.getInt("FUN_CODIGO"));
                funcionario.setNome(rs.getString("FUN_NOME"));
                funcionario.setCpf(rs.getString("FUN_CPF"));
                funcionario.setRg(rs.getString("FUN_RG"));

                venda.setFuncionario(funcionario);

                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vendas;
    }

    public List<Venda> findClienteFuncionario(String desc, boolean cli) {

        String sql;

        if (cli) {
            sql = "SELECT * FROM APP.TB_CLIENTE WHERE CLI_NOME LIKE ?";
        } else {
            sql = "SELECT * FROM APP.TB_FUNCIONARIO WHERE FUN_NOME LIKE ?";
        }

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                if (cli) {
                    Cliente cliente = new Cliente();

                    cliente.setCodigo(rs.getInt("CLI_CODIGO"));
                    cliente.setNome(rs.getString("CLI_NOME"));
                    cliente.setCpf(rs.getString("CLI_CPF"));
                    cliente.setRg(rs.getString("CLI_RG"));

                    venda.setCliente(cliente);
                } else {
                    Funcionario funcionario = new Funcionario();

                    funcionario.setCodigo(rs.getInt("FUN_CODIGO"));
                    funcionario.setNome(rs.getString("FUN_NOME"));
                    funcionario.setCpf(rs.getString("FUN_CPF"));
                    funcionario.setRg(rs.getString("FUN_RG"));

                    venda.setFuncionario(funcionario);
                }

                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vendas;
    }

    public int autoIncrement() {

        String sql = "SELECT MAX(VEN_CODIGO) AS ID FROM APP.TB_VENDA";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("ID");
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        id++;
        return id;
    }

    public boolean update(Venda venda) {

        String sql = "UPDATE APP.TB_VENDA SET VEN_CODCLIENTE = ?, VEN_CODFUNCIONARIO = ?, VEN_DATA = ? WHERE VEN_CODIGO = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getCliente().getCodigo());
            stmt.setInt(2, venda.getFuncionario().getCodigo());
            stmt.setString(3, venda.getData());
            stmt.setInt(4, venda.getCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean delete(Venda venda){
    
        String sql = "DELETE FROM APP.TB_VENDA WHERE VEN_CODIGO = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getCodigo());            
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
