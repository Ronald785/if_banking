package Factory;

public class Cliente {
    public static void main(String[] args) {
        String tipoConta;
		Conta corrente = FabricaConta.criarConta(tipoConta = "Conta Corrente");
        System.out.println(corrente.getTipoConta());

        Conta salario = FabricaConta.criarConta(tipoConta = "Conta Salario");
        System.out.println(corrente.getTipoConta());

        Conta poupanca = FabricaConta.criarConta(tipoConta = "Conta Poupanca");
        System.out.println(corrente.getTipoConta());
    }
}