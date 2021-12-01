package model.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;
import dbConnection.DBException;
import model.dao.TaxaDao;
import model.entities.Taxa;

public class TaxaDaoJDBC implements TaxaDao {

private Connection connection;
	
	public TaxaDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere uma taxa no banco de dados
	@Override
	public void insert(Taxa obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO taxa "
					+ "(id, tipo_taxa, valor) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getId());
			st.setString(2, obj.getTipoTaxa());
			st.setDouble(3, obj.getValor());
			
			int numeroDeLinhasAfetadas = st.executeUpdate();
			
			if(numeroDeLinhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					Integer id = rs.getInt(1);
					obj.setId(id);
				}
				DBConnection.closeResultSet(rs);
			}
			else {
				throw new DBException("Erro, nenhuma linha afetada");
			}
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//lê uma taxa do banco de dados
	@Override
	public Taxa read(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM taxa WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Taxa taxa = new Taxa();
				taxa.setId(rs.getInt("id"));
				taxa.setTipoTaxa(rs.getString("tipo_taxa"));
				taxa.setValor(rs.getDouble("valor"));
				return taxa;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
			DBConnection.closeResultSet(rs);
		}
	}

	//lê todas as taxas no banco de dados
	@Override
	public List<Taxa> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM taxa");
			rs = st.executeQuery();
			
			List<Taxa> list = new ArrayList<>();
			
			while(rs.next()) {
				Taxa taxa = new Taxa();
				taxa.setId(rs.getInt("id"));
				taxa.setTipoTaxa(rs.getString("tipo_taxa"));
				taxa.setValor(rs.getDouble("valor"));
				list.add(taxa);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
			DBConnection.closeResultSet(rs);
		}
	}

	//atualiza os dados de uma taxa no banco de dados
	@Override
	public void update(Taxa obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE taxa "
					+ "SET id = ?, tipo_taxa = ?, valor = ? "
					+ "WHERE id = ?");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getTipoTaxa());
			st.setDouble(3, obj.getValor());
			st.setInt(4, obj.getId());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta os dados de uma taxa do banco de dados
	@Override
	public void delete(Integer id) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM taxa WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}
}

