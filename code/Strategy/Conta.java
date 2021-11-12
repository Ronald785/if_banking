package Strategy;

public class Conta {
	protected int numeroConta;
	protected double saldo;
	protected double valorSaque;
	protected double valorChequeEspecial;
	protected String tipoConta;
	protected CalculaSaque estrategiaSaque;

	public Conta(String tipoConta, double saldo, double valorSaque, double valorChequeEspecial) {
		this.tipoConta = tipoConta;
		this.saldo = saldo;
		this.valorSaque = valorSaque;
		this.valorChequeEspecial = valorChequeEspecial;

		switch (tipoConta) {
			case "Corrente":
				estrategiaSaque = new CalculaSaqueCorrente();
				tipoConta = "Corrente";
				break;
			case "Salario":
				estrategiaSaque = new CalculaSaqueSalario();
				tipoConta = "Salario";
				break;
			case "Poupanca":
				estrategiaSaque = new CalculaSaquePoupanca();
				tipoConta = "Poupanca";
				break;
			default:
				throw new RuntimeException("Tipo de conta n�o encontrado!");
			}
	}

	public double calcularSaque() {
		return estrategiaSaque.calculaSaque(this);
	}

	public double getSaldo() {
		return saldo;
	}

	public double getValorSaque() {
		return valorSaque;
	}

	public double getValorChequeEspecial() {
		return valorChequeEspecial;
	}
}