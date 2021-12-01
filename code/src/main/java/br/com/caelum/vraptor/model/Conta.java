package br.com.caelum.vraptor.model;

import javax.persistence.Entity;

@Entity
public class Conta extends Model{
	
	private Double saldoCorrente;
	
	private Double saldoPoupanca;

	public Double getSaldoCorrente() {
		return saldoCorrente;
	}

	public void setSaldoCorrente(Double saldoCorrente) {
		this.saldoCorrente = saldoCorrente;
	}

	public Double getSaldoPoupanca() {
		return saldoPoupanca;
	}

	public void setSaldoPoupanca(Double saldoPoupanca) {
		this.saldoPoupanca = saldoPoupanca;
	}
	
}
