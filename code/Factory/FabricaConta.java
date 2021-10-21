package factory;

public class FabricaConta {
    public static Conta criarConta(string tipoConta) {
        if(tipoConta == 'Conta Corrente') {
            return new ContaCorrente();
        }
        else if(tipoConta == 'Conta Salario'){
            return new ContaSalario();
        }
        else if(tipoConta == 'Conta Poupanca'){
            return new ContaPoupanca();
        }

        return null;
    }
}