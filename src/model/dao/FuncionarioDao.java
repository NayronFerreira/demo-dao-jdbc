package model.dao;

import java.util.List;

import entidades.Funcionario;

public interface FuncionarioDao {
	
	void inser (Funcionario obj);
	void update (Funcionario obj);
	void deleteById (Integer id);
	Funcionario findById(Integer id);
	List <Funcionario> findAll ();

}
