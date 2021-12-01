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
import model.dao.ContaDao;
import model.entities.Conta;

public class ContaDaoJDBC implements ContaDao {
	
	private Connection connection;
	
	public ContaDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	//insere uma conta no banco de dados
	@Override
	public void insert(Conta obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"INSERT INTO conta "
					+ "(numero, saldo, valor_saque, valor_cheque_especial, tipo_conta) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNumeroConta());
			st.setDouble(2, obj.getSaldo());
			st.setDouble(3, obj.getValorSaque());
			st.setDouble(4, obj.getValorChequeEspecial());
			st.setString(5, obj.getTipoConta());
			
			int numeroDeLinhasAfetadas = st.executeUpdate();
			
			if(numeroDeLinhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					String numeroConta = rs.getString(4);
					obj.setNumeroConta(numeroConta);
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

	//lê uma conta do banco de dados
	@Override
	public Conta read(String numero) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement(
					"SELECT * FROM conta WHERE numero = ?");
			st.setString(1, numero);
			rs = st.executeQuery();
			if(rs.next()) {
				Conta contaStrategy = new Conta();
				contaStrategy.setNumeroConta(rs.getString("numero"));
				contaStrategy.setSaldo(rs.getDouble("saldo"));
				contaStrategy.setValorSaque(rs.getDouble("valor_saque"));
				contaStrategy.setValorChequeEspecial(rs.getDouble("valor_cheque_especial"));
				contaStrategy.setTipoConta(rs.getString("tipo_conta"));
				return contaStrategy;
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

	//lê todas as contas no banco de dados
	@Override
	public List<Conta> readAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = connection.prepareStatement("SELECT * FROM conta");
			rs = st.executeQuery();
			
			List<Conta> list = new ArrayList<>();
			
			while(rs.next()) {
				Conta contaStrategy = new Conta();
				contaStrategy.setNumeroConta(rs.getString("numero"));
				contaStrategy.setSaldo(rs.getDouble("saldo"));
				contaStrategy.setValorSaque(rs.getDouble("valor_saque"));
				contaStrategy.setValorChequeEspecial(rs.getDouble("valor_cheque_especial"));
				contaStrategy.setTipoConta(rs.getString("tipo_conta"));
				list.add(contaStrategy);
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

	//atualiza os dados de uma conta no banco de dados
	@Override
	public void update(Conta obj) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(
					"UPDATE conta "
					+ "SET numero = ?, saldo = ?, valor_saque = ?, valor_cheque_especial = ?, "
					+ "tipo_conta = ? "
					+ "WHERE numero = ?");
			st.setString(1, obj.getNumeroConta());
			st.setDouble(2, obj.getSaldo());
			st.setDouble(3, obj.getValorSaque());
			st.setDouble(4, obj.getValorChequeEspecial());
			st.setString(5, obj.getTipoConta());
			st.setString(6, obj.getNumeroConta());
			st.executeUpdate();	
		}
		catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DBConnection.closeStatement(st);
		}
	}

	//deleta dos dados de uma conta do banco de dados
	@Override
	public void delete(String numero) {
		
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("DELETE FROM conta WHERE numero = ?");
			st.setString(1, numero);
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

