package strategy;

public class CalculaSaquePoupanca implements CalculaSaque {
	@Override
	public Double calculaSaque(ContaStrategy cliente) {
		if (cliente.getSaldo() > 0 && cliente.getSaldo() > cliente.getValorSaque()) {
			return cliente.getSaldo() - cliente.getValorSaque();
		} 
        else {
            System.out.println("Saldo insuficiente");
		    return null;
        }
	}
}