package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ContaDAO;
import br.com.caelum.vraptor.dao.PessoaDAO;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.model.Conta;
import br.com.caelum.vraptor.model.Pessoa;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Path("cadastrar")
@Controller
public class CadastrarController {

	@Inject EntityManager em;
	@Inject Result result;
	@Inject PessoaDAO pessoaDao;
	@Inject ContaDAO contaDao;
	@Inject Validator validator;
	static String emailAcesso;
	
	@Get("")
	public void cadastrar() {
		
	}
	
	@IncludeParameters
	@Post("salvausuario")
	public void salvaUsuario(@Valid Pessoa pessoa, String confirmaSenha) {
		Double saldoCorrente = 0.00;
		Double saldoPoupanca = 0.00;
		//validar user
		//caso erro - tela cadastrar
		boolean asSenhasSaoIguais = confirmaSenha.equals(pessoa.getSenha());
		validator.addIf(!asSenhasSaoIguais, new SimpleMessage("confirmaSenha", "As senhas não são iguais!"));
		validator.onErrorRedirectTo(this).cadastrar();
		
		//salvar usuario no banco
		Conta conta = new Conta();
		conta.setEmail(pessoa.getEmail());
		conta.setId(pessoa.getId());
		conta.setSaldoCorrente(saldoCorrente);
		conta.setSaldoPoupanca(saldoPoupanca);
		
		emailAcesso = pessoa.getEmail();
		pessoaDao.insertOrUpdate(pessoa);
		contaDao.insertOrUpdate(conta);
		
		//direcionar para o painel
		result.redirectTo(PainelController.class).painel();
		
	}
	
	@Get("")
	public static String getEmailAcesso() {
		
		return emailAcesso;
	}
}
