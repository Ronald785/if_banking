import java.util.Date;

public class PessoaFisica extends Pessoa {

	public String cpf;

	public PessoaFisica(String nome, String endereco, String email,String cpf, int telefone, Date nascimento) {
		super(nome, endereco, email, telefone, nascimento);
	}

	public double salario;
}
