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

@Path("transferencia")
@Controller
public class TransferenciaController {


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
	public void transferencia() {

	}

	@IncludeParameters
	@Post("transferir")
	public void transferenciaBancario(Integer tipoConta, Double valorTransferencia, String emailDestinatario) {
		Conta conta = new Conta();
		String emailAcesso = LoginController.getEmailAcesso();
		if (emailAcesso == null) {
			emailAcesso = CadastrarController.getEmailAcesso();
		}

		conta.setEmail(emailAcesso);

		conta = contaDao.selectPorEmail(conta);

		if (tipoConta == 1) {
			saldo = conta.getSaldoCorrente();
			if (saldo == 0 || saldo < valorTransferencia) {
				saldoInsuficiente = true;
				validator.addIf(saldoInsuficiente, new SimpleMessage("valorTransferencia", "Saldo Insuficiente!"));
				validator.onErrorRedirectTo(this).transferencia();
			} else {
				conta.setSaldoCorrente(saldo - valorTransferencia);
			}
		} else {
			saldo = conta.getSaldoPoupanca();
			if (saldo == 0 || saldo < valorTransferencia) {
				saldoInsuficiente = true;
				validator.addIf(saldoInsuficiente, new SimpleMessage("valorTransferencia", "Saldo Insuficiente!"));
				validator.onErrorRedirectTo(this).transferencia();
			} else {
				conta.setSaldoPoupanca(saldo - valorTransferencia);
			}
			
		}

		// CONTA DESTINATARIO - DEPÓSITO
		Conta destinatario = new Conta();
		destinatario.setEmail(emailDestinatario);
		destinatario = contaDao.transferenciaPorEmail(destinatario);
		
		destinatario.setSaldoCorrente(valorTransferencia + destinatario.getSaldoCorrente());
		
		contaDao.insertOrUpdate(conta);
		contaDao.insertOrUpdate(destinatario);
		
		result.redirectTo(TransferenciaController.class).transferencia();
	}
}
