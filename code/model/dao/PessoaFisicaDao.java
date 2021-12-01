package model.dao;

import java.util.List;

import model.entities.PessoaFisica;

public interface PessoaFisicaDao {

	void insert(PessoaFisica obj);
	PessoaFisica read(String cpfOuCnpj);
	List<PessoaFisica> readAll();
	void update(PessoaFisica obj);
	void delete(String cpfOuCnpj);
}
