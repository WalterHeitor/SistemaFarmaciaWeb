package br.com.sistema.farmacia.bean;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.sistema.farmacia.DAO.FornecedoresDAO;
import br.com.sistema.farmacia.model.domain.Fornecedores;
import br.com.sistema.farmacia.util.JSFUtil;

@ManagedBean(name="MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	private List<Fornecedores>itens;
	private List<Fornecedores>itensFiltrados;
	private Fornecedores fornecedores;

	
	public List<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(List<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public List<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(List<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	/**
	 * busca por fornecedores no banco de dados e retorna uma lista 
	 * que possa prencher uma tabela de fornecedores.
	 */
	@PostConstruct
	public void prepararPesquisa() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			itens = fDAO.buscar();
			
		} catch (Exception e) {
			JSFUtil.addMensagemErro("e.getMessage()");
			// TODO: handle exception
		}
	}
	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}
	public void novo() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			fDAO.cadastrar(fornecedores);
			itens = fDAO.buscar();
			
			JSFUtil.addMensagemSucesso("Fornecedor Cadastrado com Sucesso!!!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void excluir() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			fDAO.deletar(fornecedores);
			itens = fDAO.buscar();
			
			JSFUtil.addMensagemSucesso("Fornecedor Deletado com Sucesso!!!");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.addMensagemErro("e.getMessage()");
		}
	}
	
	
	public void editar() {
		try {
			FornecedoresDAO fDAO = new FornecedoresDAO();
			fDAO.alterar(fornecedores);
			itens = fDAO.buscar();
			JSFUtil.addMensagemSucesso("Fornecedor Editado com Sucesso!!!");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.addMensagemErro("Nao e possivel editar fornecedor com produto associada a ele.");
		}
	}
	
}
