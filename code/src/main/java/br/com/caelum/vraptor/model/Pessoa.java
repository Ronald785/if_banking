package br.com.caelum.vraptor.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pessoa extends Model{
	
	@NotEmpty(message="{pessoa.nome.embranco}") @Size(min = 4, max = 20, message="{pessoa.nome.size}")
	private String nome;
	
	@NotEmpty(message="{pessoa.senha.embranco}") @Size(min = 6, max = 20, message="{pessoa.senha.size}")
	private String senha;

	@NotEmpty(message="{pessoa.cpf.embranco}") @Size(min = 11, max = 11, message="{pessoa.cpf.size}")
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	 
}
