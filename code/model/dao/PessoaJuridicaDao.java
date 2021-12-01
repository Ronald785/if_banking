package model.dao;

import java.util.List;

import model.entities.PessoaJuridica;

public interface PessoaJuridicaDao {

	void insert(PessoaJuridica obj);
	PessoaJuridica read(String cpfOuCnpj);
	List<PessoaJuridica> readAll();
	void update(PessoaJuridica obj);
	void delete(String cpfOuCnpj);
}
