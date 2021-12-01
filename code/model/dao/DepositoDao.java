package model.dao;

import java.util.List;

import model.entities.Deposito;

public interface DepositoDao {

	void insert(Deposito obj);
	Deposito read(Long id);
	List<Deposito> readAll();
	void update(Deposito obj);
	void delete(Long id);
}
