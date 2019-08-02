package br.com.sistema.farmacia.model.domain;

public class Produtos {

	private Long codigo_pro;
	private String descricao_pro;
	private Long quant;
	private Double preco;
	private Fornecedores fornecedores = new Fornecedores();
	public Long getCodigo_pro() {
		return codigo_pro;
	}
	public void setCodigo_pro(Long codigo_pro) {
		this.codigo_pro = codigo_pro;
	}
	public String getDescricao_pro() {
		return descricao_pro;
	}
	public void setDescricao_pro(String descricao_pro) {
		this.descricao_pro = descricao_pro;
	}
	public Long getQuant() {
		return quant;
	}
	public void setQuant(Long quant) {
		this.quant = quant;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Fornecedores getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_pro == null) ? 0 : codigo_pro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		if (codigo_pro == null) {
			if (other.codigo_pro != null)
				return false;
		} else if (!codigo_pro.equals(other.codigo_pro))
			return false;
		return true;
	}
	
	public Produtos() {
		super();
	}
	public Produtos(String descricao_pro, Long quant, Double preco, Fornecedores fornecedores) {
		super();
		this.descricao_pro = descricao_pro;
		this.quant = quant;
		this.preco = preco;
		this.fornecedores = fornecedores;
	}
	
	
}
