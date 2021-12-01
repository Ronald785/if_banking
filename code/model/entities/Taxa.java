package model.entities;

public class Taxa {

	private Integer id;
	private String tipoTaxa;
	private Double valor;
	
	public Taxa() {
		
	}
	
	public Taxa(Integer id, String tipoTaxa, Double valor) {
		this.id = id;
		this.tipoTaxa = tipoTaxa;
		this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoTaxa() {
		return tipoTaxa;
	}

	public void setTipoTaxa(String tipoTaxa) {
		this.tipoTaxa = tipoTaxa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Taxa other = (Taxa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
