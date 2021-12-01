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
import model.dao.SaqueDao;
import model.entities.Saque;

public class SaqueDaoJDBC implements SaqueDao {

private Connection connection;
	
	public SaqueDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere um saque no banco de dados
	@Override
	public void insert(Saque obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO saque "
					+ "(id, cpf_saque, valor, valor_taxa) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, obj.getId());
			st.setString(2, obj.getCpfSaque());
			st.setDouble(3, obj.getValor());
			st.setDouble(4, obj.getValorTaxa());
			
			int numeroDeLinhasAfetadas = st.executeUpdate();
			
			if(numeroDeLinhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					Long id = rs.getLong(1);
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

	//lê um saque do banco de dados
	@Override
	public Saque read(Long id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM saque WHERE id = ?");
			st.setLong(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Saque saque = new Saque();
				saque.setId(rs.getLong("id"));
				saque.setCpfSaque(rs.getString("cpf_saque"));
				saque.setValor(rs.getDouble("valor"));
				saque.setValorTaxa(rs.getDouble("valor_taxa"));
				return saque;
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

	//lê todos os saques no banco de dados
	@Override
	public List<Saque> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM saque");
			rs = st.executeQuery();
			
			List<Saque> list = new ArrayList<>();
			
			while(rs.next()) {
				Saque saque = new Saque();
				saque.setId(rs.getLong("id"));
				saque.setCpfSaque(rs.getString("cpf_saque"));
				saque.setValor(rs.getDouble("valor"));
				saque.setValorTaxa(rs.getDouble("valor_taxa"));
				list.add(saque);
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

	//atualiza os dados de um saque no banco de dados
	@Override
	public void update(Saque obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE saque "
					+ "SET id = ?, cpf_saque = ?, valor = ?, valor_taxa = ? "
					+ "WHERE id = ?");
			st.setLong(1, obj.getId());
			st.setString(2, obj.getCpfSaque());
			st.setDouble(3, obj.getValor());
			st.setDouble(4, obj.getValorTaxa());
			st.setLong(5, obj.getId());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta os dados de um saque do banco de dados
	@Override
	public void delete(Long id) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM saque WHERE id = ?");
			st.setLong(1, id);
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

