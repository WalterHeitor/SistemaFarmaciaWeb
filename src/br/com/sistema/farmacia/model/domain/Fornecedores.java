package br.com.sistema.farmacia.model.domain;

public class Fornecedores {

	private Long codigo_for;
	private String descricao_for;
	
	public Long getCodigo_for() {
		return codigo_for;
	}
	public void setCodigo_for(Long codigo_for) {
		this.codigo_for = codigo_for;
	}
	public String getDescricao_for() {
		return descricao_for;
	}
	public void setDescricao_for(String descricao_for) {
		this.descricao_for = descricao_for;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_for == null) ? 0 : codigo_for.hashCode());
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
		Fornecedores other = (Fornecedores) obj;
		if (codigo_for == null) {
			if (other.codigo_for != null)
				return false;
		} else if (!codigo_for.equals(other.codigo_for))
			return false;
		return true;
	}
	public Fornecedores() {
		super();
	}
	public Fornecedores(String descricao_for) {
		super();
		this.descricao_for = descricao_for;
	}
	
	
}
