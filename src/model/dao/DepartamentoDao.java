package model.dao;

import java.util.List;

import entidades.Departamento;

public interface DepartamentoDao {
	
	void inser (Departamento obj);
	void update (Departamento obj);
	void deleteById (Integer id);
	Departamento findById(Integer id);
	List <Departamento> findAll ();

}
