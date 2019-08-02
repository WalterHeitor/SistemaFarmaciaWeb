package br.com.sistema.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.farmacia.factory.FabricaConexao;
import br.com.sistema.farmacia.model.domain.Fornecedores;
import br.com.sistema.farmacia.model.domain.Produtos;

public class ProdutoDAO {

	private Connection con = null;
	/**
	 * salva dados em tabela produtos.
	 * 
	 * @param p variavel produtos.
	 */
	public void cadastrar(Produtos p) {
		
		try {
			String sql = "INSERT INTO PRODUTOS(descricao_pro, quant, preco, fornecedores_codigo_for)"
					+ " values(?, ?, ?, ?)";
			con = FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getDescricao_pro());
			ps.setLong(2, p.getQuant());
			ps.setDouble(3, p.getPreco());
			ps.setLong(4, p.getFornecedores().getCodigo_for());
			ps.execute();
			FabricaConexao.closeConnection(con, ps);
			System.out.println("Cadastrado com sucesso!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao isnerir dados ao fornecedores: " + e.getMessage());
		}
	}
	/**
	 * altera dados em tabela produtos.
	 * 
	 * @param p variavel produtos.
	 */
	public void alterar(Produtos p) {
		
		try {
			String sql = "UPDATE PRODUTOS SET "
					+ "descricao_pro = ?, quant = ?, preco = ?, fornecedores_codigo_for=? "
					+ "WHERE codigo_pro=?";
			con = FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getDescricao_pro());
			ps.setLong(2, p.getQuant());
			ps.setDouble(3, p.getPreco());
			ps.setLong(4, p.getFornecedores().getCodigo_for());
			ps.setLong(5, p.getCodigo_pro());
			ps.execute();
			FabricaConexao.closeConnection(con, ps);
			System.out.println("Alterado com sucesso!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao alterar dados ao fornecedores: " + e.getMessage());
		}
	}
	/**
	 * Deleta um prduto da tabela produtos
	 * @param p variavel produtos.
	 */
	public void deletar(Produtos p) {
		try {
			String sql = "DELETE FROM PRODUTOS WHERE codigo_pro=?";
			con = FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, p.getCodigo_pro());
			ps.execute();
			FabricaConexao.closeConnection(con, ps);
			System.out.println("deletado com sucesso!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao deletar dados ao fornecedores: " + e.getMessage());
		}		
	}
	/**
	 * economia de codigo podendo alterar se existir codigo e salvar
	 * se for null onde existe uma condição if.
	 * @param f variavel fornecedores.
	 */
	public void salvar(Produtos p) {
		if(p.getCodigo_pro()!=null && p.getCodigo_pro()!=0) {
			alterar(p);
		}else {
			cadastrar(p);
		}
	}
	public List<Produtos> buscar(){
		List<Produtos> listaProd = new ArrayList<>();
		try {
			con=FabricaConexao.getConection();
			String sql = "SELECT produtos.codigo_pro, produtos.descricao_pro, produtos.quant, produtos.preco,"
					+ "f.codigo_for, f.descricao_for "
					+ "FROM produtos "
					+ "INNER JOIN FORNECEDORES f  ON f.codigo_for = produtos.fornecedores_codigo_for";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				Fornecedores f = new Fornecedores();
				f.setCodigo_for(rs.getLong("f.codigo_for"));
				f.setDescricao_for(rs.getString("f.descricao_for"));
				
				Produtos p = new Produtos();
				p.setCodigo_pro(rs.getLong("produtos.codigo_pro"));
				p.setDescricao_pro(rs.getString("produtos.descricao_pro"));
				p.setQuant(rs.getLong("produtos.quant"));
				p.setPreco(rs.getDouble("produtos.preco"));
				p.setFornecedores(f);
				listaProd.add(p);
				
			}
			FabricaConexao.closeConnection(con, ps, rs);
			System.out.println("buscado com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("erro ao buscar lista de Produtos: "+e.getMessage());
			e.printStackTrace();
		}
		return listaProd;
	}
	/**
	 * busca lista de fornecedores na tabela de fornecedores   e coloca em resultSet
	 * e coloca em uma lista de fornecedores 
	 * @return retorna uma lista de fornecedores 
	 */
	public List<Fornecedores> buscarDescricao(Fornecedores f){
		List<Fornecedores> listaFornec = new ArrayList<>();
		try {
			con=FabricaConexao.getConection();
			String sql = "SELECT f.codigo_for, f.descricao_for,"
					+ " p.codigo_pro, p.descricao_pro, p.preco, p.quant"
					+ "FROM FORNECEDORES WHERE descricao_for LIKE  ? "
					+ "ORDER BY descricao_for ASC";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,"%"+ f.getDescricao_for() + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fornecedores itemF = new Fornecedores();
				itemF.setCodigo_for(rs.getLong("codigo_for"));
				itemF.setDescricao_for(rs.getString("descricao_for"));
				listaFornec.add(itemF);
			}
			FabricaConexao.closeConnection(con, ps, rs);
			System.out.println("buscado com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("erro ao buscar lista de fornecedor: "+e.getMessage());
			e.printStackTrace();
		}
		return listaFornec;
	}
	
}
