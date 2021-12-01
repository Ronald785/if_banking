package br.com.caelum.vraptor.dao;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.controller.LoginController;
import br.com.caelum.vraptor.controller.TransferenciaController;
import br.com.caelum.vraptor.model.Model;
import br.com.caelum.vraptor.model.Pessoa;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;

/**
 * Classe Generica que mantem metodos basicos de persistencia de objetos
 * no banco de dados
 *
 */
public abstract class DAO <T extends Model> {

	@Inject Validator validator;
	protected EntityManager em;
	private Class<T> persistedClass;
	
	public DAO(EntityManager em, Class<T> persistedClass) {
		this.em = em;
		this.persistedClass = persistedClass;
	}
	
	/**
	 * Persiste um Objeto passado como parâmetro no banco de dados
	 * @param object
	 */
	public T insert(T model) {
		em.persist(model);
		return model;
	}

	/**
	 * Persiste ou atualiza um objeto passado como parâmetro no banco de dados
	 * @param object
	 */
	public T insertOrUpdate(T model) { 
		
		if(model.getId() < 1) {
			return insert(model);
		}
		
		return update(model);
		
	}
	
	public T update(T model) {
		model = em.merge(model);
		em.persist(model);
		return model;
	}
	
	/**
	 * Deleta um Objeto passado como parâmetro no banco de dados
	 * OBS: Este Objeto deve ter o ID informado
	 * @param object
	 */
	public void delete(T model) {
		if(model!= null && model.getId() < 1) {
			throw new RuntimeException("Não foi Possível deletar pois o id é 0 ou inválido!  classe do registro:"+ model.getClass().getSimpleName() + " id do registro: "+model.getId());
		}
		model = em.find(persistedClass, model.getId());
		em.remove(model);
	}
	
	/**
	 * Traz um Registro pesquisando por id no banco de dados
	 * @param model
	 * @return
	 */
	public T selectPorId(T model) {
		
		int id = model.getId();
		model = em.find(this.persistedClass, model.getId());
		
		//Verifica se o Registro esta inativo no banco de dados caso inativo estoura uma exception
		if(model == null) {
			
			throw new RuntimeException(
					"O Registro que tentou buscar, não existe no Banco de Dados! classe do registro:" + persistedClass.getSimpleName() + 
					" id do registro: "+id);
		}
		
		return model; //Sera retornado o registro encontrado, ou nulo, ou se tiver inativo estoura a exeption
		
	}
	
	public T selectPorEmail(T login) {

		login = em.find(this.persistedClass, login.getEmail());

		// Verifica se o Registro esta inativo no banco de dados caso inativo estoura
		// uma exception
	
		validator.addIf(login == null, new SimpleMessage("login.email", "Email ou senha incorretos!"));
		validator.onErrorRedirectTo(LoginController.class).login();

		return login; // Sera retornado o registro encontrado, ou nulo, ou se tiver inativo estoura a
						// exeption
	}
	
	public T transferenciaPorEmail(T transferir) {

		transferir = em.find(this.persistedClass, transferir.getEmail());

		// Verifica se o Registro esta inativo no banco de dados caso inativo estoura
		// uma exception
	
		validator.addIf(transferir == null, new SimpleMessage("transferir.email", "Email do destinatário incorreto!"));
		validator.onErrorRedirectTo(TransferenciaController.class).transferencia();

		return transferir; // Sera retornado o registro encontrado, ou nulo, ou se tiver inativo estoura a
						// exeption
	}
	
	/**
	 * @param model
	 * @return
	 * @throws RegistroInativoException
	 */
	
	protected String stringFormaterSearch(String valor) {
		return valor.toUpperCase().trim()+"%";
	}
	
	
}
