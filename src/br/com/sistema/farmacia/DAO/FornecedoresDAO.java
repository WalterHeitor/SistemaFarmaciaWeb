package br.com.sistema.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.farmacia.factory.FabricaConexao;
import br.com.sistema.farmacia.model.domain.Fornecedores;

public class FornecedoresDAO {
	private Connection con = null;

	/**
	 * salva dados em tabela fornecedores.
	 * 
	 * @param f variavel fornecedores.
	 */
	public void cadastrar(Fornecedores f) {
		
		try {
			String sql = "INSERT INTO FORNECEDORES(descricao_for) values(?)";
			con = FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, f.getDescricao_for());
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
	 * altera dados em tabela fornecdores.
	 * 
	 * @param f variavel fornecedores.
	 */
	public void alterar(Fornecedores f) {
		
		try {
			String sql = "UPDATE FORNECEDORES SET descricao_for=? WHERE codigo_for=?";
			con = FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, f.getDescricao_for());
			ps.setLong(2, f.getCodigo_for());
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
	 * Deleta dados em tabela fornecdores pelo codigo_for.
	 * 
	 * @param f variavel fornecedores.
	 */
	public void deletar(Fornecedores f) {
		
		try {
			String sql = "DELETE FROM FORNECEDORES WHERE codigo_for=?";
			con = FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, f.getCodigo_for());
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
	public void salvar(Fornecedores f) {
		if(f.getCodigo_for()!=null && f.getCodigo_for()!=0) {
			alterar(f);
		}else {
			cadastrar(f);
		}
	}
	/**
	 * busca lista de fornecedores na tabela de fornecedores   e coloca em resultSet
	 * e coloca em uma lista de fornecedores 
	 * @return retorna uma lista de fornecedores 
	 */
	public List<Fornecedores> buscar(){
		List<Fornecedores> listaFornec = new ArrayList<>();
		try {
			con=FabricaConexao.getConection();
			String sql = "SELECT *FROM FORNECEDORES";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fornecedores f = new Fornecedores();
				f.setCodigo_for(rs.getLong("codigo_for"));
				f.setDescricao_for(rs.getString("descricao_for"));
				listaFornec.add(f);
			}
			FabricaConexao.closeConnection(con, ps, rs);
			System.out.println("buscado com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("erro ao buscar lista de fornecedor: "+e.getMessage());
			e.printStackTrace();
		}
		return listaFornec;
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
			String sql = "SELECT codigo_for, descricao_for "
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
	/**
	 * busca fornecedores por codigo_for 
	 * @param id = codigo_for 
	 * @return retorna fornecedores f .
	 */
	public Fornecedores buscarID(Long id){
		con=FabricaConexao.getConection();
		String sql = "SELECT codigo_for, descricao_for FROM FORNECEDORES WHERE codigo_for = ?";
		Fornecedores f = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				f= new Fornecedores();
				f.setCodigo_for(rs.getLong("codigo_for"));
				f.setDescricao_for(rs.getString("descricao_for"));
			}
			FabricaConexao.closeConnection(con, ps, rs);
			System.out.println("buscado fornecedores com sucesso!!!");
		} catch (SQLException e) {
			System.out.println("erro ao buscar por id "+e.getMessage());
			e.printStackTrace();
		}
		return f;
	}
	/**
	 * procura por fornecedores metodo booleano
	 * @param f variavel fornecedores.
	 * @return retorno boolean verdadeiro ou falso
	 */
	public Boolean exiteFornecedor(Fornecedores f) {
		
		try {
			String sql = "SELECT *FROM FORNECEDORES WHERE codigo_for=?, descricao=?";
			con=FabricaConexao.getConection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, f.getCodigo_for());
			ps.setString(2, f.getDescricao_for());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("erro ao buscar fornecedor "+e.getMessage());
			e.printStackTrace();
		}
		
		return false;		
	}
}
