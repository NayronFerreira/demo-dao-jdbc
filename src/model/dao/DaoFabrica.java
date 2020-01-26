package model.dao;

import db.DB;
import model.dao.impl.FuncionarioDaoIJDBC;

public class DaoFabrica {
	
	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoIJDBC(DB.getConnection());
	}

}
