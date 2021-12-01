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
import model.dao.DepositoDao;
import model.entities.Deposito;

public class DepositoDaoJDBC implements DepositoDao {

private Connection connection;
	
	public DepositoDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere um deposito no banco de dados
	@Override
	public void insert(Deposito obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO deposito "
					+ "(id, cpf_deposito, valor) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, obj.getId());
			st.setString(2, obj.getCpfDeposito());
			st.setDouble(3, obj.getValor());
			
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

	//lê um deposito do banco de dados
	@Override
	public Deposito read(Long id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM deposito WHERE id = ?");
			st.setLong(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Deposito deposito = new Deposito();
				deposito.setId(rs.getLong("id"));
				deposito.setCpfDeposito(rs.getString("cpf_deposito"));
				deposito.setValor(rs.getDouble("valor"));
				return deposito;
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

	//lê todos os depositos no banco de dados
	@Override
	public List<Deposito> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM deposito");
			rs = st.executeQuery();
			
			List<Deposito> list = new ArrayList<>();
			
			while(rs.next()) {
				Deposito deposito = new Deposito();
				deposito.setId(rs.getLong("id"));
				deposito.setCpfDeposito(rs.getString("cpf_deposito"));
				deposito.setValor(rs.getDouble("valor"));
				list.add(deposito);
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

	//atualiza os dados de um deposito no banco de dados
	@Override
	public void update(Deposito obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE deposito "
					+ "SET id = ?, cpf_deposito = ?, valor = ?, "
					+ "WHERE id = ?");
			st.setLong(1, obj.getId());
			st.setString(2, obj.getCpfDeposito());
			st.setDouble(3, obj.getValor());
			st.setLong(4, obj.getId());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta os dados de um deposito do banco de dados
	@Override
	public void delete(Long id) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM deposito WHERE id = ?");
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

