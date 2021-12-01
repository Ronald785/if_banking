import java.util.Date;

public class Transacoes {

	private String movimento;
	private Date data;

	public Transacoes() {
	}
	
	public Transacoes(String movimento, Date data) {
		this.movimento = movimento;
		this.data = data;
	}

	public String getMovimento() {
		return movimento;
	}

	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void registrar(String movimento, Date data) {
		//algo a fazer
	}
}
