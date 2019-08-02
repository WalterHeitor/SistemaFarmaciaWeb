package br.com.sistema.farmacia.teste;


import java.util.List;

import org.junit.Test;

import br.com.sistema.farmacia.DAO.ProdutoDAO;
import br.com.sistema.farmacia.model.domain.Fornecedores;
import br.com.sistema.farmacia.model.domain.Produtos;
import jdk.nashorn.internal.ir.annotations.Ignore;


public class produtoDAOtest {

	
	@Test
	public  void testCadastrar() {
		try {
			Produtos p1 = new Produtos();
			p1.setDescricao_pro("aspirina");
			p1.setPreco(2.98);
			p1.setQuant(12L);
			Fornecedores f = new Fornecedores();
			f.setCodigo_for(1L);
			p1.setFornecedores(f);
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.cadastrar(p1);
		} catch (Exception e) {
			System.out.println("erro ao realizar test"+e.getMessage());
		}

	}
	@Ignore
	public void listar() {
		ProdutoDAO pdao = new ProdutoDAO();
		List<Produtos>lista = pdao.buscar();
		for(Produtos p: lista) {
			System.out.println(p.getCodigo_pro()+ " "+p.getDescricao_pro()+ " "+p.getPreco()+" "
					+p.getQuant()+" "+p.getFornecedores().getCodigo_for());
		}
	}
	@Ignore
	public void deletar() {
		Produtos p = new Produtos();
		p.setCodigo_pro(2L);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.deletar(p);
	}
	@Ignore
	public void editar() {
		Produtos p = new Produtos();
		p.setCodigo_pro(3L);
		p.setDescricao_pro("dorflex");
		p.setQuant(9L);
		p.setPreco(5.49);
		Fornecedores f = new Fornecedores();
		f.setCodigo_for(13L);
		p.setFornecedores(f);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.alterar(p);;
	}
}
