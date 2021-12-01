package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

@Path("login")
@Controller
public class LoginController {

	@Inject EntityManager em;
	@Inject Result result;
	@Inject PessoaDAO pessoaDao;
	@Inject ContaDAO contaDao;
	@Inject Validator validator;
	static String emailAcesso;
	static Double saldoCorrente;
	static Double saldoPoupanca;
	
	@Get("")
	public void login() {
		
	}
	
	@IncludeParameters
	@Post("acessar")
	public void acessarUsuario(Pessoa login) {
		String senha = login.getSenha();
		emailAcesso = login.getEmail();
		//selecionar usuario no banco
		String confirmaSenha = pessoaDao.selectPorEmail(login).getSenha();
		
		Conta conta = new Conta();
		conta.setEmail(emailAcesso);
		
		saldoCorrente = contaDao.selectPorEmail(conta).getSaldoCorrente();
		saldoPoupanca = contaDao.selectPorEmail(conta).getSaldoPoupanca();
		//validar user
			//caso erro - tela login
		boolean asSenhasSaoIguais = confirmaSenha.equals(senha);
		validator.addIf(!asSenhasSaoIguais, new SimpleMessage("login.senha", "Email ou senha incorretos!"));
		validator.onErrorRedirectTo(this).login();
		//direcionar para o painel
		result.redirectTo(PainelController.class).painel();
	}
	
	@Get("")
	public static String getEmailAcesso() {
		
		return emailAcesso;
	}
}
