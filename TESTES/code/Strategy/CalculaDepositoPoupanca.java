package Strategy;

public class CalculaDepositoPoupanca implements CalculaDeposito {

	@Override
	public double calculaDeposito(Conta cliente) {
		return cliente.getSaldo() + cliente.getValorDeposito();
	}

}
