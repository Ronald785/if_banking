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

@Path("deposito")
@Controller
public class DepositoController {

	@Inject EntityManager em;
	@Inject Result result;
	@Inject ContaDAO contaDao;
	@Inject Validator validator;

	@Get("")
	public void deposito() {
		
	}
	
	@IncludeParameters
	@Post("depositar")
	public void depositoBancario(Integer tipoConta, Double valorDeposito) {
		Conta conta = new Conta();
		String emailAcesso = LoginController.getEmailAcesso();
		if(emailAcesso == null) {
			emailAcesso = CadastrarController.getEmailAcesso();
		}
				
		conta.setEmail(emailAcesso);
		
		conta = contaDao.selectPorEmail(conta);
		
		if(tipoConta == 1) {
			conta.setSaldoCorrente(valorDeposito + conta.getSaldoCorrente());
		}else {
			conta.setSaldoPoupanca(valorDeposito + conta.getSaldoPoupanca());
		}
		
		contaDao.insertOrUpdate(conta);
		result.redirectTo(DepositoController.class).deposito();
	}
}
