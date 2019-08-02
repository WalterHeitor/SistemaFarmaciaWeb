package teste;

import java.util.List;

import br.com.sistema.farmacia.DAO.FornecedoresDAO;
import br.com.sistema.farmacia.factory.FabricaConexao;
import br.com.sistema.farmacia.model.domain.Fornecedores;

public class testeFarmacia {

	public static void main(String[] args) {		
		//chamarConexao();
		testCadastrar();
		//testAlterar();
		//testdeletar();
		//testBuscar();
		//testBuscarDescricao();
		
	}

	public static void chamarConexao() {
		FabricaConexao.getConection();
	}
	public static void testCadastrar() {
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao_for("polaramida");
		Fornecedores f2 = new Fornecedores();
		f2.setDescricao_for("EMS");
		FornecedoresDAO fDAO = new FornecedoresDAO();
		fDAO.cadastrar(f1);
		fDAO.cadastrar(f2);	
	}
	public static void testAlterar() {
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo_for(2L);
		f1.setDescricao_for("biomedic");
		FornecedoresDAO fDAO = new FornecedoresDAO();
		fDAO.alterar(f1);
			
	}
	public static void testdeletar() {
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo_for(2L);
		FornecedoresDAO fDAO = new FornecedoresDAO();
		fDAO.deletar(f1);
			
	}
	public static void testBuscar() {
		FornecedoresDAO fDAO = new FornecedoresDAO();
		List<Fornecedores> listForn = fDAO.buscar();
		for(Fornecedores f : listForn) {
			System.out.println(f.getCodigo_for()+" "+f.getDescricao_for());
		}
	}
	public static void testBuscarDescricao() {
		Fornecedores f = new Fornecedores();
		f.setDescricao_for("E");
		FornecedoresDAO fDAO = new FornecedoresDAO();
		List<Fornecedores> listForn = fDAO.buscarDescricao(f);
		for(Fornecedores f1 : listForn) {
			System.out.println(f1.getCodigo_for()+" "+f1.getDescricao_for());
		}
	}
}
