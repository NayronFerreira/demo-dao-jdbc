package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbExcecao;
import entidades.Departamento;
import entidades.Funcionario;
import model.dao.FuncionarioDao;

public class FuncionarioDaoIJDBC implements FuncionarioDao {

	private Connection conn;

	public FuncionarioDaoIJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inser(Funcionario obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Funcionario obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Funcionario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setNome(rs.getString("DepName"));
				Funcionario func = new Funcionario();
				func.setId(rs.getInt("Id"));
				func.setAniversario(rs.getDate("BirthDate"));
				func.setEmail(rs.getString("Email"));
				func.setSalario(rs.getDouble("BaseSalary"));
				func.setNome("Name");
				func.setDepartamento(dep);
				return func;
			}

			return null;
		} catch (SQLException x) {
			throw new DbExcecao(x.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			
		}
	}

	@Override
	public List<Funcionario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
