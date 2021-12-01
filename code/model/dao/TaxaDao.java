package model.dao;

import java.util.List;

import model.entities.Taxa;

public interface TaxaDao {

	void insert(Taxa obj);
	Taxa read(Integer id);
	List<Taxa> readAll();
	void update(Taxa obj);
	void delete(Integer id);
}
