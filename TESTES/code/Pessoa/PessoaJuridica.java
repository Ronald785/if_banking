import java.util.Date;

public class PessoaJuridica extends Pessoa {

	public PessoaJuridica(String nome, String endereco, String email, int telefone, Date nascimento) {
		super(nome, endereco, email, telefone, nascimento);
	}

	public double salario;
}
