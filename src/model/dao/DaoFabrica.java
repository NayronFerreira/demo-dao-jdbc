package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.FuncionarioDaoIJDBC;

public class DaoFabrica {
	
	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoIJDBC(DB.getConnection());
	}
	public static DepartamentoDao createDepartamentoDao() {
		return new DepartamentoDaoJDBC(DB.getConnection());
	}
}
