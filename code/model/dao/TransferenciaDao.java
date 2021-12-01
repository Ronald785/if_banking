package model.dao;

import java.util.List;

import model.entities.Transferencia;

public interface TransferenciaDao {
	void insert(Transferencia obj);
	Transferencia read(String numero);
	List<Transferencia> readAll();
	void update(Transferencia obj);
	void delete(String numero);
}
