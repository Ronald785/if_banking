package br.com.caelum.vraptor.model;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Classe Representa um Modelo no Sistema
 * encapsula propriedades que deveram ser persistidas no banco
 */
@MappedSuperclass
//@Audited(withModifiedFlag=true)
public abstract class Model {

	@Id
	@NotEmpty(message="{pessoa.email.embranco}") @Email(message="{email.email}")
	private String email;
	
	@Column
	//@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
