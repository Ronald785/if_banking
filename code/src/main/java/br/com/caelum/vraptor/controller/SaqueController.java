package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ContaDAO;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.model.Conta;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

@Path("saque")
@Controller
public class SaqueController {

	@Inject
	EntityManager em;
	@Inject
	Result result;
	@Inject
	ContaDAO contaDao;
	@Inject
	Validator validator;
	double saldo;
	boolean saldoInsuficiente;

	@Get("")
	public void saque() {

	}

	@IncludeParameters
	@Post("sacar")
	public void saqueBancario(Integer tipoConta, Double valorSaque) {
		Conta conta = new Conta();
		String emailAcesso = LoginController.getEmailAcesso();
		if (emailAcesso == null) {
			emailAcesso = CadastrarController.getEmailAcesso();
		}

		conta.setEmail(emailAcesso);

		conta = contaDao.selectPorEmail(conta);

		if (tipoConta == 1) {
			saldo = conta.getSaldoCorrente();
			if (saldo == 0 || saldo < valorSaque) {
				saldoInsuficiente = true;
				validator.addIf(saldoInsuficiente, new SimpleMessage("valorSaque", "Saldo Insuficiente!"));
				validator.onErrorRedirectTo(this).saque();
			} else {
				conta.setSaldoCorrente(saldo - valorSaque);
			}
		} else {
			saldo = conta.getSaldoPoupanca();
			if (saldo == 0 || saldo < valorSaque) {
				saldoInsuficiente = true;
				validator.addIf(saldoInsuficiente, new SimpleMessage("valorSaque", "Saldo Insuficiente!"));
				validator.onErrorRedirectTo(this).saque();
			} else {
				conta.setSaldoPoupanca(saldo - valorSaque);
			}
			
		}

		contaDao.insertOrUpdate(conta);
		result.redirectTo(SaqueController.class).saque();
	}
}
