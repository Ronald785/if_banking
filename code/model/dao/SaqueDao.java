package model.dao;

import java.util.List;

import model.entities.Saque;

public interface SaqueDao {

	void insert(Saque obj);
	Saque read(Long id);
	List<Saque> readAll();
	void update(Saque obj);
	void delete(Long id);
}
