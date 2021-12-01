package factory;

public class Cliente {
    public static void main(String[] args) {
        Conta corrente = FabricaConta.criarConta("Conta Corrente");
        System.out.println(corrente.getTipoConta());

        Conta salario = FabricaConta.criarConta("Conta Salario");
        System.out.println(salario.getTipoConta());

        Conta poupanca = FabricaConta.criarConta("Conta Poupanca");
        System.out.println(poupanca.getTipoConta());
    }
}