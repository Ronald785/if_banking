package strategy;

public class Teste {
	public static void main(String[] args) {

		ContaStrategy clienteCorrente = new ContaStrategy("Corrente", 5000.00, 500.00, 7000.00);
		System.out.println(clienteCorrente.calcularSaque());
		
		ContaStrategy clienteSalario = new ContaStrategy("Salario", 2200.00, 2200.00, 40000.00);
		System.out.println(clienteSalario.calcularSaque());

        ContaStrategy clientePoupanca = new ContaStrategy("Poupanca", 900.00, 4344.00, 23233.00);
		System.out.println(clientePoupanca.calcularSaque());
	}
}