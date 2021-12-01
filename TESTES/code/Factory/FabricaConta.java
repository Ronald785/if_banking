package Factory;

public class FabricaConta {
    public static Conta criarConta(String tipoConta) {
        if(tipoConta == "Conta Corrente") {
            return new ContaCorrente();
        }
        else if(tipoConta == "Conta Salario"){
            return new ContaSalario();
        }
        else if(tipoConta == "Conta Poupanca"){
            return new ContaPoupanca();
        }

        return null;
    }
}