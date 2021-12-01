package Strategy;

public class CalculaSaqueSalario implements CalculaSaque {
	@Override
	public double calculaSaque(Conta cliente) {
		if (cliente.getSaldo() > 0 && cliente.getSaldo() > cliente.getValorSaque()) {
			return cliente.getSaldo() - cliente.getValorSaque();
		} 
        else {
            System.out.println("Saldo insuficiente");
		    return 0;
        }
	}
}