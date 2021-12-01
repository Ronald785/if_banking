package Strategy;

public class Conta {
	public static String Corrente;
	public static String Salario;
	public static String Poupanca;
	protected int numeroConta;
	protected double saldo;
	protected double valorSaque;
	protected double valorDeposito;
	protected double valorChequeEspecial;
	protected String tipoConta;
	protected CalculaSaque estrategiaSaque;
	protected CalculaDeposito estrategiaDeposito;

	public Conta(String tipoConta, double saldo, double valorSaque, int i) {
		this.tipoConta = tipoConta;
		this.saldo = saldo;
		this.valorSaque = valorSaque;
		this.valorChequeEspecial = valorChequeEspecial;

		switch (tipoConta) {
		case "Corrente":
			estrategiaSaque = new CalculaSaqueCorrente();
			estrategiaDeposito = new CalculaDepositoCorrente();
			tipoConta = "Corrente";
			break;
		case "Salario":
			estrategiaSaque = new CalculaSaqueSalario();
			estrategiaDeposito = new CalculaDepositoSalario();
			tipoConta = "Salario";
			break;
		case "Poupanca":
			estrategiaSaque = new CalculaSaquePoupanca();
			estrategiaDeposito = new CalculaDepositoPoupanca();
			tipoConta = "Poupanca";
			break;
		default:
			throw new RuntimeException("Tipo de Conta não encontrado!");
		}
	}

	

	public Conta(String poupanca2, int i) {
		// TODO Auto-generated constructor stub
	}

	public Conta(String salario2, int i, int j) {
		// TODO Auto-generated constructor stub
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
	
	public double calcularDeposito() {
		return estrategiaDeposito.calculaDeposito(this);
	}

	public double getValorDeposito() {
		return valorDeposito;
	}

}