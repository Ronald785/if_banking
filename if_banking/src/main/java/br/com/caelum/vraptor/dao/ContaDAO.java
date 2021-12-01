package br.com.caelum.vraptor.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.model.Conta;
import br.com.caelum.vraptor.model.Pessoa;

@RequestScoped
public class ContaDAO extends DAO<Conta>{

	@Inject
	public ContaDAO(EntityManager em) {
		super(em, Conta.class);
	}
	
	@Deprecated
	public ContaDAO() {
		this(null);
	}

}
