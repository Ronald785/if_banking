package model.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TesteCliente {
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		PessoaFisica pessoaFisica = new PessoaFisica("Pablo Escobar", "Rua do Pó", "elguaco@pbe.noia", "(123)9999-8888", sdf.parse("01/01/1981"), 5000.00, "111.222.333-44");
		System.out.println("\nNome: " + pessoaFisica.nome);
		System.out.println("Endereco: " + pessoaFisica.endereco);
		System.out.println("Email: " + pessoaFisica.email);
		System.out.println("Telefone: " + pessoaFisica.telefone);
		System.out.println("CPF: " + pessoaFisica.cpf);
		System.out.println("Data de nascimento: " + pessoaFisica.getNascimento());
	}
}