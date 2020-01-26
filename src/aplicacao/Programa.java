package aplicacao;

import entidades.Funcionario;
import model.dao.DaoFabrica;
import model.dao.FuncionarioDao;

public class Programa {

	public static void main(String[] args) {

	
		
		FuncionarioDao funcDao = DaoFabrica.createFuncionarioDao();
		
		Funcionario func = funcDao.findById(3);
		
		System.out.println(func);
		
		
	}

}
