package aplicacao;

import java.util.Date;

import entidades.Departamento;
import entidades.Funcionario;
import model.dao.DaoFabrica;
import model.dao.FuncionarioDao;

public class Programa {

	public static void main(String[] args) {

		Departamento dep = new Departamento(1, "Nayron");
		
		Funcionario func = new Funcionario(10, "Ingrid", "nathalia@gmail.com", new Date(), 3000.0, dep);
		
		FuncionarioDao funcDao = DaoFabrica.createFuncionarioDao();
		
		System.out.println(func);
		
		System.out.println(dep);
	}

}
