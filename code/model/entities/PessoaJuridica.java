package model.entities;

import java.util.Date;

public class PessoaJuridica extends Pessoa {
	
	public Double salario;
	private String cnpj;

	public PessoaJuridica() {
	}
	
	public PessoaJuridica(String nome, String endereco, String email, String telefone, Date nascimento, Double salario, String cnpj) {
		super(nome, endereco, email, telefone, nascimento);
		this.salario = salario;
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
}
