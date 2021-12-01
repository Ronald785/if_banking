package model.entities;

public class Conta {

	private String numeroConta;
	private Double saldo;
	private Double valorSaque;
	private Double valorChequeEspecial;
	private String tipoConta;
	
	public Conta() {
	}
	
	public Conta(String numeroConta, Double saldo, Double valorSaque, Double valorChequeEspecial, String tipoConta) {
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.valorSaque = valorSaque;
		this.valorChequeEspecial = valorChequeEspecial;
		this.tipoConta = tipoConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getValorSaque() {
		return valorSaque;
	}

	public void setValorSaque(Double valorSaque) {
		this.valorSaque = valorSaque;
	}

	public Double getValorChequeEspecial() {
		return valorChequeEspecial;
	}

	public void setValorChequeEspecial(Double valorChequeEspecial) {
		this.valorChequeEspecial = valorChequeEspecial;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

//	public CalculaSaque getEstrategiaSaque() {
//		return estrategiaSaque;
//	}
//
//	public void setEstrategiaSaque(CalculaSaque estrategiaSaque) {
//		this.estrategiaSaque = estrategiaSaque;
//	}
//
//	public Double calcularSaque() {
//		return estrategiaSaque.calculaSaque(this);
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
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
		Conta other = (Conta) obj;
		if (numeroConta == null) {
			if (other.numeroConta != null)
				return false;
		} else if (!numeroConta.equals(other.numeroConta))
			return false;
		return true;
	}
}