package br.com.caelum.vraptor.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.model.Pessoa;

@RequestScoped
public class PessoaDAO extends DAO<Pessoa>{

	@Inject
	public PessoaDAO(EntityManager em) {
		super(em, Pessoa.class);
	}
	
	@Deprecated
	public PessoaDAO() {
		this(null);
	}

}
