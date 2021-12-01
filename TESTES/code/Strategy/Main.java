package Strategy;

public class Main {
	public static void main(String[] args) {

		Conta clienteCorrente = new Conta(Conta.Corrente, 5000, 500, 7000);
		System.out.println(clienteCorrente.calcularSaque());
		
		Conta clienteSalario = new Conta(Conta.Salario, 2200, 2200);
		System.out.println(clienteSalario.calcularSaque());

        Conta clientePoupanca = new Conta(Conta.Poupanca, 900);
		System.out.println(clientePoupanca.calcularSaque());
	}
}