package factory;

public interface Conta {
    String getTipoConta();

	void sacar();
	void depositar();
	void transferir();
}