package aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Departamento;
import entidades.Funcionario;
import model.dao.DaoFabrica;
import model.dao.FuncionarioDao;

public class Programa {

	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		FuncionarioDao funcDao = DaoFabrica.createFuncionarioDao();
		System.out.println("=== Teste 1: funcionario findById ===");
		Funcionario func = funcDao.findById(3);
		System.out.println(func);

		System.out.println();

		System.out.println("=== Teste 2: funcionario findByDepartment ===");
		Departamento dep = new Departamento(1, null);
		List<Funcionario> lista = funcDao.findByDepartamento(dep);
		for (Funcionario x : lista) {
			System.out.println(x);
		}

		System.out.println();

		System.out.println("=== Teste 3: funcionario findByAll ===");

		lista = funcDao.findAll();
		lista.forEach(System.out::println);

		System.out.println();

		System.out.println("=== Teste 4 funcionario insert ===");
		Funcionario fun = new Funcionario(null, "Nayron Ferreira", "nayron@gmail.cim", new Date(), 3000.0, dep);
		funcDao.insert(fun);
		System.out.println("Inseriso! Novo Id = " + fun.getId());

		System.out.println();

		System.out.println("=== Teste 5 funcionario update ===");
		func = funcDao.findById(1);
		func.setNome("Nathalia Santanna");
		funcDao.update(func);
		System.out.println("update completo!!");
		
		System.out.println();

		System.out.println("=== Teste  funcionario deleteById ===");
		System.out.print("Informe um Id para remoção:");
		int id = leia.nextInt();
		funcDao.deleteById(id);

		
		leia.close();
	}

}
