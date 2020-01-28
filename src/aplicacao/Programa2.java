package aplicacao;

import entidades.Departamento;
import model.dao.DaoFabrica;
import model.dao.DepartamentoDao;

public class Programa2 {

	public static void main(String[] args) {
		
		
		
		DepartamentoDao depdao = DaoFabrica.createDepartamentoDao();
		
		Departamento dep = depdao.findById(3);
		
		System.out.println(dep);
		
		System.out.println("=== Teste 1: Insert === ");
//		depdao.insert(dep);
		
		System.out.println();
		
		System.out.println("=== Teste 2: update === ");
		
		dep.setNome("QAT");
		depdao.update(dep);
		System.out.println(dep);
	}

}
