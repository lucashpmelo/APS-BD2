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
import model.bean.Produto;
import model.bean.ProdutoVenda;
import model.bean.Venda;

/**
 *
 * @author lucas
 */
public class ProdutoVendaDAO {

    private Connection con = null;

    public ProdutoVendaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean save(ProdutoVenda produtovenda) {

        String sql = "INSERT INTO APP.TB_PRODUTO_VENDA (PROVEN_CODIGO, PROVEN_CODVENDA, PROVEN_CODPRODUTO, PROVEN_QUANTIDADE, PROVEN_VLRUNITARIO) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produtovenda.getCodigo());
            stmt.setInt(2, produtovenda.getVenda().getCodigo());
            stmt.setInt(3, produtovenda.getProduto().getCodigo());
            stmt.setInt(4, produtovenda.getQuantidade());
            stmt.setDouble(5, produtovenda.getValor());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<ProdutoVenda> findAll() {

        String sql = "SELECT * FROM APP.TB_PRODUTO_VENDA, APP.TB_PRODUTO, APP.TB_VENDA WHERE PROVEN_CODVENDA = VEN_CODIGO AND PROVEN_CODPRODUTO = PRO_CODIGO";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutoVenda> produtovendas = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoVenda produtovenda = new ProdutoVenda();

                produtovenda.setCodigo(rs.getInt("PROVEN_CODIGO"));
                produtovenda.setQuantidade(rs.getInt("PROVEN_QUANTIDADE"));
                produtovenda.setValor(rs.getDouble("PROVEN_VLRUNITARIO"));

                Venda venda = new Venda();

                venda.setCodigo(rs.getInt("VEN_CODIGO"));
                venda.setData(rs.getString("VEN_DATA"));

                Cliente cliente = new Cliente();

                cliente.setCodigo(rs.getInt("VEN_CODCLIENTE"));
                venda.setCliente(cliente);

                Funcionario funcionario = new Funcionario();

                funcionario.setCodigo(rs.getInt("VEN_CODFUNCIONARIO"));
                venda.setFuncionario(funcionario);

                produtovenda.setVenda(venda);

                Produto produto = new Produto();

                produto.setCodigo(rs.getInt("PRO_CODIGO"));
                produto.setDescricao(rs.getString("PRO_DESCRICAO"));
                produto.setEstoque(rs.getInt("PRO_ESTOQUE"));
                produtovenda.setProduto(produto);

                produtovendas.add(produtovenda);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtovendas;
    }
    
    public List<ProdutoVenda> find(int ID) {

        String sql = "SELECT * FROM APP.TB_PRODUTO_VENDA, APP.TB_PRODUTO, APP.TB_VENDA WHERE PROVEN_CODVENDA = VEN_CODIGO AND PROVEN_CODPRODUTO = PRO_CODIGO AND VEN_CODIGO = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutoVenda> produtovendas = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ID);
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoVenda produtovenda = new ProdutoVenda();

                produtovenda.setCodigo(rs.getInt("PROVEN_CODIGO"));
                produtovenda.setQuantidade(rs.getInt("PROVEN_QUANTIDADE"));
                produtovenda.setValor(rs.getDouble("PROVEN_VLRUNITARIO"));

                Venda venda = new Venda();

                venda.setCodigo(rs.getInt("VEN_CODIGO"));
                venda.setData(rs.getString("VEN_DATA"));

                Cliente cliente = new Cliente();

                cliente.setCodigo(rs.getInt("VEN_CODCLIENTE"));
                venda.setCliente(cliente);

                Funcionario funcionario = new Funcionario();

                funcionario.setCodigo(rs.getInt("VEN_CODFUNCIONARIO"));
                venda.setFuncionario(funcionario);

                produtovenda.setVenda(venda);

                Produto produto = new Produto();

                produto.setCodigo(rs.getInt("PRO_CODIGO"));
                produto.setDescricao(rs.getString("PRO_DESCRICAO"));
                produto.setEstoque(rs.getInt("PRO_ESTOQUE"));
                produtovenda.setProduto(produto);

                produtovendas.add(produtovenda);
            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtovendas;
    }

    public int autoIncrement() {

        String sql = "SELECT MAX(PROVEN_CODIGO) AS ID FROM APP.TB_PRODUTO_VENDA";

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
    
    public boolean delete(ProdutoVenda produtovenda){
    
        String sql = "DELETE FROM APP.TB_PRODUTO_VENDA WHERE PROVEN_CODVENDA = ?";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produtovenda.getVenda().getCodigo());            
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }        
    }
    
    public boolean deleteIten(ProdutoVenda produtovenda){
    
        String sql = "DELETE FROM APP.TB_PRODUTO_VENDA WHERE PROVEN_CODVENDA = ? AND PROVEN_QUANTIDADE = ? AND PROVEN_CODPRODUTO = (SELECT PRO_CODIGO FROM APP.TB_PRODUTO WHERE PRO_DESCRICAO = ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produtovenda.getVenda().getCodigo());
            stmt.setInt(2, produtovenda.getQuantidade());
            stmt.setString(3, produtovenda.getProduto().getDescricao());
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
