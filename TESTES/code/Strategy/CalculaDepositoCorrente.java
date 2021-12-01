package Strategy;

public class CalculaDepositoCorrente implements CalculaDeposito {

	@Override
	public double calculaDeposito(Conta cliente) {
		return cliente.getSaldo() + cliente.getValorDeposito();
	}

}
