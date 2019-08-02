package br.com.sistema.farmacia.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistema.farmacia.DAO.FornecedoresDAO;
import br.com.sistema.farmacia.DAO.ProdutoDAO;
import br.com.sistema.farmacia.model.domain.Fornecedores;
import br.com.sistema.farmacia.model.domain.Produtos;
import br.com.sistema.farmacia.util.JSFUtil;

@ManagedBean(name="MBProdutos")
@ViewScoped
public class ProdutosBean {

	private List<Produtos>itens;
	private List<Produtos>itensFiltrados;
	private Produtos produtos;
	private List<Fornecedores>comboFornecedores;
	
	public List<Produtos> getItens() {
		return itens;
	}
	public void setItens(List<Produtos> itens) {
		this.itens = itens;
	}
	public List<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(List<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	public List<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	public void setComboFornecedores(List<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	/**
	 * busca por fornecedores no banco de dados e retorna uma lista 
	 * que possa prencher uma tabela de fornecedores.
	 */
	@PostConstruct
	public void prepararPesquisa() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			itens = pDAO.buscar();
			
		} catch (Exception e) {
			JSFUtil.addMensagemErro("e.getMessage()");
			// TODO: handle exception
		}
	}
	
	public void prepararNovo() {
		try {
			produtos = new Produtos();
			FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.buscar();
		} catch (Exception e) {
			JSFUtil.addMensagemErro("e.getMessage()");
		}
	}
	public void prepararEditar() {
		try {
			produtos = new Produtos();
			FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.buscar();
		} catch (Exception e) {
			JSFUtil.addMensagemErro("e.getMessage()");
		}
		
	}
	public void novo() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			pDAO.cadastrar(produtos);
			itens = pDAO.buscar();
			
			JSFUtil.addMensagemSucesso("Produto Cadastrado com Sucesso!!!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void excluir() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			pDAO.deletar(produtos);
			itens = pDAO.buscar();
			
			JSFUtil.addMensagemSucesso("Produto Deletado com Sucesso!!!");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.addMensagemErro("e.getMessage()");
		}
	}
	public void editar() {
		try {
			ProdutoDAO pDAO = new ProdutoDAO();
			pDAO.alterar(produtos);;
			itens = pDAO.buscar();
			JSFUtil.addMensagemSucesso("Produto Editado com Sucesso!!!");
		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.addMensagemErro("Nao e possivel editar fornecedor com produto associada a ele.");
		}
	}
}
