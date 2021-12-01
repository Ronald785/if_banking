package model.entities;

import java.util.Date;

public class PessoaFisica extends Pessoa {

	public Double salario;
	public String cpf;

	public PessoaFisica() {
	}
	
	public PessoaFisica(String nome, String endereco, String email, String telefone, Date nascimento, Double salario, String cpf) {
		super(nome, endereco, email, telefone, nascimento);
		this.salario = salario;
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
}
