package model.dao;

import java.util.List;

import entidades.Departamento;
import entidades.Funcionario;

public interface FuncionarioDao {
	
	void insert (Funcionario obj);
	void update (Funcionario obj);
	void deleteById (Integer id);
	Funcionario findById(Integer id);
	List <Funcionario> findAll ();
	List <Funcionario> findByDepartamento (Departamento departamento);
}
