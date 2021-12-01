package model.entities;

public class Saque {

	private Long id;
	private String cpfSaque;
	private Double valor;
	private Double valorTaxa;
	
	public Saque() {
		
	}

	public Saque(Long id, String cpfSaque, Double valor, Double valorTaxa) {
		super();
		this.id = id;
		this.cpfSaque = cpfSaque;
		this.valor = valor;
		this.valorTaxa = valorTaxa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfSaque() {
		return cpfSaque;
	}

	public void setCpfSaque(String cpfSaque) {
		this.cpfSaque = cpfSaque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(Double valorTaxa) {
		this.valorTaxa = valorTaxa;
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
		Saque other = (Saque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
