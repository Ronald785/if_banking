package model.entities;

public class Transferencia {
	
	private Long id;
	private String cpfEnvio;
	private String cpfRecebimento;
	private Double valor;
	private Double valorTaxa;
	
	public Transferencia() {
		
	}
	
	public Transferencia(Long id, String cpfEnvio, String cpfRecebimento, Double valor, Double valorTaxa) {
		this.id = id;
		this.cpfEnvio = cpfEnvio;
		this.cpfRecebimento = cpfRecebimento;
		this.valor = valor;
		this.valorTaxa = valorTaxa;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfEnvio() {
		return cpfEnvio;
	}

	public void setCpfEnvio(String cpfEnvio) {
		this.cpfEnvio = cpfEnvio;
	}

	public String getCpfRecebimento() {
		return cpfRecebimento;
	}

	public void setCpfRecebimento(String cpfRecebimento) {
		this.cpfRecebimento = cpfRecebimento;
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
		Transferencia other = (Transferencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
