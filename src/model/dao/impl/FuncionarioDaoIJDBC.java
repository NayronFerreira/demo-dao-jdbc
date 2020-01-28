package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insert(Funcionario obj) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO seller\r\n"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)\r\n" + "VALUES\r\n" + "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getSalario());
			st.setInt(5, obj.getDepartamento().getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
				}
			} else {
				throw new DbExcecao("Erro inesperado! Sem linhas afetadas");
			}
		} catch (SQLException x) {
			throw new DbExcecao(x.getMessage());
		}

		finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void update(Funcionario obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE seller "
					+"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+"WHERE Id = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getSalario());
			st.setInt(5, obj.getDepartamento().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException x) {
			throw new DbExcecao(x.getMessage());
		}

		finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM seller\r\n" + 
					"WHERE Id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException x) {
			throw new DbExcecao(x.getMessage());
		}

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
				Departamento dep = instanciarDepartamento(rs);
				Funcionario func = instanciarFuncionario(rs, dep);
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

	private Funcionario instanciarFuncionario(ResultSet rs, Departamento dep) throws SQLException {
		Funcionario func = new Funcionario();
		func.setId(rs.getInt("Id"));
		func.setAniversario(rs.getDate("BirthDate"));
		func.setEmail(rs.getString("Email"));
		func.setSalario(rs.getDouble("BaseSalary"));
		func.setNome(rs.getString("Name"));
		func.setDepartamento(dep);
		return func;
	}

	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Funcionario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName\r\n" + "FROM seller INNER JOIN department\r\n"
							+ "ON seller.DepartmentId = department.Id\r\n" + "ORDER BY Name");

			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<Funcionario>();
			Map<Integer, Departamento> map = new HashMap<Integer, Departamento>();

			while (rs.next()) {

				Departamento depart = map.get(rs.getInt("DepartmentId"));
				if (depart == null) {
					depart = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), depart);
				}

				Funcionario func = instanciarFuncionario(rs, depart);
				lista.add(func);

			}

			return lista;
		} catch (SQLException x) {
			throw new DbExcecao(x.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);

		}

	}

	@Override
	public List<Funcionario> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n" + "ON seller.DepartmentId = department.Id\r\n"
					+ "WHERE DepartmentId = ? " + "ORDER BY Name");

			st.setInt(1, departamento.getId());
			rs = st.executeQuery();

			List<Funcionario> lista = new ArrayList<Funcionario>();
			Map<Integer, Departamento> map = new HashMap<Integer, Departamento>();

			while (rs.next()) {

				Departamento depart = map.get(rs.getInt("DepartmentId"));
				if (depart == null) {
					depart = instanciarDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), depart);
				}

				Funcionario func = instanciarFuncionario(rs, depart);
				lista.add(func);

			}

			return lista;
		} catch (SQLException x) {
			throw new DbExcecao(x.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);

		}
	}

}
