package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbExcecao;
import entidades.Departamento;
import model.dao.DepartamentoDao;

public class DepartamentoDaoJDBC implements DepartamentoDao {

	private Connection conn;

	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public void insert(Departamento obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO department "
					+ "(Id, Name) " 
					+ "VALUES "
					+ "(?, ?) ");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getNome());
			

			int linhasAfetadas = st.executeUpdate();
			System.out.println("Linhas afetadas: "+linhasAfetadas);

			
		} catch (SQLException x) {
			throw new DbExcecao(x.getMessage());
		}

		finally {
			DB.closeStatement(st);

		}
		
	}

	@Override
	public void update(Departamento obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE department "
					+"SET Name = ? "
					+"WHERE Id = ? ");
			
			
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
			
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
		// TODO Auto-generated method stub

	}

	@Override
	public Departamento findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT * FROM department "
					+ "WHERE Id = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = instanciarDepartamento(rs);
				
				
				return dep;
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
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
