package model.dao;

import model.dao.impl.FuncionarioDaoIJDBC;

public class DaoFabrica {
	
	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoIJDBC();
	}

}
