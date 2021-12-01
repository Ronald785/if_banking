package model.dao;

import java.util.List;

import model.entities.Conta;

public interface ContaDao {

	void insert(Conta obj);
	Conta read(String numero);
	List<Conta> readAll();
	void update(Conta obj);
	void delete(String numero);
}