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
import model.dao.TransferenciaDao;
import model.entities.Transferencia;

public class TransferenciaDaoJDBC implements TransferenciaDao {

private Connection connection;
	
	public TransferenciaDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere uma transferencia no banco de dados
	@Override
	public void insert(Transferencia obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO transferencia "
					+ "(id, cpf_envio, cpf_recebimento, valor, valor_taxa) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, obj.getId());
			st.setString(2, obj.getCpfEnvio());
			st.setString(3, obj.getCpfRecebimento());
			st.setDouble(4, obj.getValor());
			st.setDouble(5, obj.getValorTaxa());
			
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

	//lê uma transferencia do banco de dados
	@Override
	public Transferencia read(String numero) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM transferencia WHERE numero = ?");
			st.setString(1, numero);
			rs = st.executeQuery();
			if(rs.next()) {
				Transferencia transferencia = new Transferencia();
				transferencia.setId(rs.getLong("id"));
				transferencia.setCpfEnvio(rs.getString("cpf_envio"));
				transferencia.setCpfRecebimento(rs.getString("cpf_recebimento"));
				transferencia.setValor(rs.getDouble("valor"));
				transferencia.setValorTaxa(rs.getDouble("valor_taxa"));
				return transferencia;
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

	//lê todas as transferencias no banco de dados
	@Override
	public List<Transferencia> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM transferencia");
			rs = st.executeQuery();
			
			List<Transferencia> list = new ArrayList<>();
			
			while(rs.next()) {
				Transferencia transferencia = new Transferencia();
				transferencia.setId(rs.getLong("id"));
				transferencia.setCpfEnvio(rs.getString("cpf_envio"));
				transferencia.setCpfRecebimento(rs.getString("cpf_recebimento"));
				transferencia.setValor(rs.getDouble("valor"));
				transferencia.setValorTaxa(rs.getDouble("valor_taxa"));
				list.add(transferencia);
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

	//atualiza os dados de uma transferencia no banco de dados
	@Override
	public void update(Transferencia obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE transferencia "
					+ "SET id = ?, cpf_envio = ?, cpf_recebimento = ?, valor = ?, "
					+ "valor_taxa = ? "
					+ "WHERE id = ?");
			st.setLong(1, obj.getId());
			st.setString(2, obj.getCpfEnvio());
			st.setString(3, obj.getCpfRecebimento());
			st.setDouble(4, obj.getValor());
			st.setDouble(5, obj.getValorTaxa());
			st.setLong(6, obj.getId());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta dos dados de uma transferencia do banco de dados
	@Override
	public void delete(String id) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM transferencia WHERE id = ?");
			st.setString(1, id);
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

