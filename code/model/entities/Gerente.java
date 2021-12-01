package model.entities;

import java.util.Date;
import pessoa.PessoaFisica;

public class Gerente extends PessoaFisica {

	public Gerente(String nome, String endereco, String email, String telefone, Date nascimento, Double salario, String cpf) {
		super(nome, endereco, email, telefone, nascimento, salario, cpf);
	}
	
	public void fechar_conta() {

	}

	public void consultar_credito() {

	}
}
