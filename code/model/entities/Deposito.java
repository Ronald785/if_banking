package model.entities;

public class Deposito {

	private Long id;
	private String cpfDeposito;
	private Double valor;
	
	public Deposito() {
		
	}
	
	public Deposito(Long id, String cpfDeposito, Double valor) {
		this.id = id;
		this.cpfDeposito = cpfDeposito;
		this.valor = valor;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfDeposito() {
		return cpfDeposito;
	}

	public void setCpfDeposito(String cpfDeposito) {
		this.cpfDeposito = cpfDeposito;
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
		Deposito other = (Deposito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
