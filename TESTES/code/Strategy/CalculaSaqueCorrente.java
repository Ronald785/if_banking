
package Strategy;

public class CalculaSaqueCorrente implements CalculaSaque {
	@Override
	public double calculaSaque(Conta cliente) {
		if (cliente.getSaldo() > 0 && cliente.getSaldo() > cliente.getValorSaque()) {
			return cliente.getSaldo() - cliente.getValorSaque();
		}
        else if (cliente.getSaldo() + cliente.getValorChequeEspecial() > cliente.getValorSaque()) {
        	System.out.println("Usufruindo do cheque especial");
			return (cliente.getSaldo() + cliente.getValorChequeEspecial())  - cliente.getValorSaque();
        }
        else {
            System.out.println("Saldo insuficiente");
		    return 0;
        }
	}
}