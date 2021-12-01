package Strategy;

public class CalculaDepositoSalario implements CalculaDeposito {

	@Override
	public double calculaDeposito(Conta cliente) {
		return cliente.getSaldo() + cliente.getValorDeposito();
	}

}
